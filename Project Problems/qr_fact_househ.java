import java.io.File;
import java.util.Scanner;
/**
    *This class represents the Householder Reflection of some matrix
    @author Hexiang Li
    @version 1.0
*/
public class qr_fact_househ extends LinearAlgebra {

    public static double[][] Hilbert;
    public static double[][] Q;
    public static double[][] R;

    public static void reflection(int size) throws IllegalOperandException {
        Q = new double[size][size];
        R = new double[size][size];

        // initialize Q as identity
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == j){
                    Q[i][j] = 1;
                } else {
                    Q[i][j] = 0;
                }
            }
        }

        // initialize an identity matrix based on Hilbert matrix's size
        double[][] I = new double[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == j){
                    I[i][j] = 1;
                } else {
                    I[i][j] = 0;
                }
            }
        }

        // The householder reflection
        for (int i = 0; i < size; i++) {
            // getting the first coloumn of Matrix(say it's vector X):
            double[] X = new double[size];
            for (int j = 0; j < size; j++) {
                X[j] = Hilbert[j][i];
                if (j < i) {
                    X[j] = 0;
                }
            }

            Vector vX = new Vector(X);

            // C is the norm of first coloumn times e
            double[] C = new double[size];
            for (int c = 0; c < size; c++) {
                if (c == i) {
                    C[i] = magnitude(vX);
                } else {
                C[c] = 0;
                }
            }
            Vector vC = new Vector(C);

            // Don't need to make any change after reaching the last row of Matrix
            if (i != size - 1) {
                double[][] U = new double[size][1];
                double[] U1 = vectorSubtract(vX, vC).getArray();

                //Turns U into a 2D array(Matrix):
                for (int k = 0; k < size; k++) {
                    U[k][0] = U1[k];
                }

                // following calculations are based on the householder reflection equation
                double[][] UT = transpose(new Matrix(U)).getArray();
                double[][] UUT = matrixMultiply(new Matrix(U),
                    new Matrix(UT)).getArray();
                double div = 2 / (magnitude(new Vector(U1))
                    * magnitude(new Vector(U1)));
                double[][] TwoUUT = scalarMultiply(div,
                    new Matrix(UUT)).getArray();
                double[][] Ha = matrixSubtract(new Matrix(I),
                    new Matrix(TwoUUT)).getArray();


                for (int row = 0; row < size; row++) {
                    for (int column = 0; column < size; column++) {
                        if (row == column && row <= (i - 1)) {
                            Ha[row][column] = 1;
                        }
                    }
                }

                R = matrixMultiply(new Matrix(Ha), new Matrix(Hilbert)).getArray();
                for (int m = 0; m < size; m++) {
                    for (int n = 0; n < size; n++) {
                        Hilbert[m][n] = R[m][n];
                    }
                }
                Q = matrixMultiply(new Matrix(Q), new Matrix(Ha)).getArray();
            }
        }
    }

    /**
        *Gets the Q matrix after householder reflection
        @return Matrix Q
    */
    public static Matrix getQ() throws IllegalOperandException {
        return new Matrix(Q);
    }

    /**
        *Gets the R matrix after householder reflection
        @return Matrix R
    */
    public static Matrix getR() throws IllegalOperandException {
        return new Matrix(R);
    }

    /**
     * Sets the matrix to be used
     * USE THIS METHOD FIRST TO INITIALIZE
     * @param m1 Matrix to be used
     * @throws IllegalOperandException
     */
    public static void setMatrix(Matrix m1) throws IllegalOperandException {
        Hilbert = m1.getArray();
        reflection(m1.getWidth());
    }

    public static void main(String[] args) throws Exception {
        LinearAlgebraScanner input = new LinearAlgebraScanner();

        System.out.println("Press 1 to enter matrix through terminal");
        System.out.println("Press 2 to enter matrix through a file");
        Matrix m1;
        String inputTypeLine = input.nextLine();
        int inputType = Integer.parseInt(inputTypeLine);
        if (inputType == 1) {
            System.out.println("Please enter a matrix!");
            System.out.println("Enter empty line to terminate!");
            m1 = input.readMatrix();
        } else {
            System.out.println("Enter file path.");
            String path = input.nextLine();
            File file = new File(path);
            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
            m1 = fileInput.readMatrixFromFile();
        }
        qr_fact_househ.setMatrix(m1);
        Matrix q = qr_fact_househ.getQ();
        Matrix r = qr_fact_househ.getR();
        System.out.println("Q = \t\n" + q.toString());
        System.out.println("R = \t\n" + r.toString());
        double error = LinearAlgebra.matrixMaxNorm(
                LinearAlgebra.matrixSubtract(LinearAlgebra.matrixMultiply(q, r)
                        , m1));
        System.out.println("Error = " + error);
        System.out.println("Q*R = \t\n" + LinearAlgebra.matrixMultiply(q, r));
    }
}
