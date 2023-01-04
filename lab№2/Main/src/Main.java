public class Main {
    public static void main(String[] args) {
        Matrix matr = new Matrix(3, 3);
        Matrix matr1 = new Matrix(3);
        Matrix matr3 = new Matrix();
        Matrix matr2 = new Matrix(matr);

        //ImmutableMatrix matr28 = new ImmutableMatrix(matr);
        System.out.println(matr.equals(matr1));

        //matr1.show();
        matr2.show();
        matr.Inverse(matr1);


        //System.out.println(Arrays.toString(matr.size_of_matrix()));
        //System.out.println(matr.equals(matr1));
        //System.out.println("----------");
        //System.out.println(matr.equals(matr1));

    }
}