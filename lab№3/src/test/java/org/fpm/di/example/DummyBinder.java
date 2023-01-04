package org.fpm.di.example;

import org.fpm.di.Binder;

import javax.inject.Singleton;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class DummyBinder implements Binder {
    private ArrayList<Class<?>> data = new ArrayList<>();
    private HashMap<Class<?>, Class<?>> data1 = new HashMap<>();
    private HashMap<Class<?>, Object> data2 = new HashMap<>();

    @Override
    public <T> void bind(Class<T> clazz) {
        if (clazz.getAnnotation(Singleton.class)!=null) {
            try {
                data2.put(clazz, clazz.getConstructor().newInstance());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        else
            data.add(clazz);
    }

    @Override
    public <T> void bind(Class<T> clazz, Class<? extends T> implementation) {
        data1.put(clazz,implementation);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        data2.put(clazz,instance);
    }

    public <T> Class<T> getData(Class<T> clazz){
        return data.contains(clazz)? clazz : null;
    }

    public <T> Class<T> getData1(Class<T> clazz){
        return data1.containsKey(clazz)? (Class<T>) data1.get(clazz) : null;
    }

    public <T> T getData2(Class<T> clazz){
        return data2.containsKey(clazz)? (T) data2.get(clazz) : null;
    }
}
