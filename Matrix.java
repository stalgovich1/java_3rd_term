import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;


public class Matrix {
    private double[][] current_matrix;
    private int row;
    private int column;

    public void createMatrix(int row, int column){
        current_matrix = new double[row][column];
        this.row = row;
        this.column = column;
    }

    // Filling our matrix
    public void fill_matrix(){
        int min = -10;
        int max = 10;

        for (int i = 0; i < this.row; i++)
        {
            for (int j = 0; j < this.column; j++)
            {
                current_matrix[i][j] = (int)(Math.random()*(max-min+1)+min);
            }
        }
    }

    public int[] sizeOfMatrix(){;
        return new int[] {this.row, this.column};
    }


    // Constructors
    public Matrix(){
        createMatrix(0,0);
    }
    public Matrix(int size){
        createMatrix(size, size);
        for (int i = 0; i < this.row; i++)
        {
            for (int j = 0; j < this.column; j++)
            {
                if (i == j){
                    current_matrix[i][j] = 1;
                }else {
                    current_matrix[i][j] = 0;
                }
            }
        }
    }

    public Matrix(int row, int column){
        createMatrix(row, column);
        fill_matrix();
    }

    public Matrix(Matrix matrix) {
        int row = matrix.sizeOfMatrix()[0];
        int column = matrix.sizeOfMatrix()[1];
        createMatrix(row, column);

        for (int i = 0; i < this.row; i++)
        {
            for (int j = 0; j < this.column; j++)
            {
                current_matrix[i][j] = matrix.getElement(i,j);
            }
        }
    }

    // Getters
    public int getElement(int row, int column){return (int)current_matrix[row][column];}
    public double[] getRow(int row){return current_matrix[row-1];}
    public double[] getColumn(int column){
        double[] column1 = new double[column];

        for (int i = 0; i < this.row; i++)
        {
            for (int j = 0; j < this.column; j++)
            {
                if (column-1 == j){
                    column1[i] = current_matrix[i][j];
                }
            }
        }
        return column1;
    }

    // Setter
    public void setElement(int row, int column, double val){
        current_matrix[row-1][column-1]=val;
    }

    // Inverse
    public void rowsAdd(int i, Matrix unit){
        for (int j = 0; j<this.row; j++)
            if (i != j && this.current_matrix[j][i] != 0) {
                for (int d = 0;d<this.row;d++) {
                    this.current_matrix[i][d] += this.current_matrix[j][d];
                    unit.current_matrix[i][d] += unit.current_matrix[j][d];
                }
                break;
            }
    }

    public void diagonal_zero_check(Matrix unit){
        for (int i = 0; i<this.row; i++) {
            if (this.current_matrix[i][i] == 0) {
                rowsAdd(i, unit);
            }
        }
    }

    public void Inverse(Matrix unit){
        DecimalFormat df = new DecimalFormat("##.##");

        this.diagonal_zero_check(unit);

        for (int i = 0; i<this.row; i++) {
            double temp = current_matrix[i][i];
            for (int j = 0; j<this.row; j++) {
                current_matrix[i][j] = Double.parseDouble(df.format(current_matrix[i][j] /= temp));
                unit.current_matrix[i][j] = Double.parseDouble(df.format(unit.current_matrix[i][j] /= temp));
            }
            rowsSubtraction(i, current_matrix, unit.current_matrix);
        }

        System.out.println("Result: ");
        unit.show();
    }

    public void rowsSubtraction(int i, double[][] matrix, double[][] unit){
        DecimalFormat df = new DecimalFormat("##.##");

        for (int j = 0;j<matrix.length;j++) {
            if (i != j) {
                double temp1 = matrix[j][i];
                for (int k = 0; k<matrix.length; k++) {
                    matrix[j][k] = Double.parseDouble(df.format(matrix[j][k] -= temp1 * matrix[i][k]));
                    unit[j][k] = Double.parseDouble(df.format(unit[j][k] -= temp1 * unit[i][k]));

                    if (unit[j][k] > Double.MAX_VALUE) {
                        System.out.println("Inverse matrix doesn't exist!");
                        return;
                    }
                }
            }
        }
    }


    // Equals/hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Matrix matrix = (Matrix) o;
        int row = matrix.sizeOfMatrix()[0];
        int column = matrix.sizeOfMatrix()[1];

        if (this.row != row || this.column != column){
            return false;
        }


        for (int i = 0; i < this.row; i++) {
            for (int j = 0; j < this.column; j++) {
                if (current_matrix[i][j] != matrix.getElement(i, j)){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return 31 * Arrays.deepHashCode(current_matrix);
    }

    public void show() {
        for (double[] rows : current_matrix) {
            for (double element : rows) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
}