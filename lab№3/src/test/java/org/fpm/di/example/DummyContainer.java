package org.fpm.di.example;

import org.fpm.di.Container;

import javax.inject.Inject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class DummyContainer implements Container {
    DummyBinder binder;
    public DummyContainer(DummyBinder binder) {
        this.binder = binder;
    }

    @Override
    public <T> T getComponent(Class<T> clazz) {
        Class<T> returnedC;
        T returned;
        if ((returnedC=binder.getData(clazz))!=null){
            if((returned=inject(clazz))!=null){
                return returned;
            }
            try {
                return returnedC.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        if ((returnedC=binder.getData1(clazz))!=null){
            return getComponent(returnedC);
        }
        if ((returned=binder.getData2(clazz))!=null){
            return returned;
        }
        return null;
    }

    private <T> T inject(Class<T> clazz){
        for (Constructor<?> constructor: clazz.getConstructors()){
            if (constructor.getAnnotation(Inject.class)!=null){
                ArrayList<Object> ret = new ArrayList<>();
                for (Class<?> returned: constructor.getParameterTypes()){
                    ret.add(getComponent(returned));
                }
                try {
                    return (T) constructor.newInstance(ret.toArray());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
