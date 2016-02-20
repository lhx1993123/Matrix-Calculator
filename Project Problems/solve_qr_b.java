import java.io.File;
import java.util.Scanner;
/**
    *This class solves a Ax = b system based on QR factorization
    @author Hexiang Li
    @version 1.0
*/
public class solve_qr_b extends LinearAlgebra {
    /**
     * Returns solution x after solving the system by using householder reflection
     *
     * @param A matrix A
     * @param b matrix b
     * @return solution x
     */
    public static Matrix solveH(Matrix A, Matrix b) throws IllegalOperandException {
        int size = A.getHeight();
        double[][] x = new double[1][size];

        /**Matrix R = qr_fact_househ.getR(A);
        Matrix Q = qr_fact_househ.getQ(A);
        Matrix Qt = transpose(Q);
        Matrix B = matrixMultiply(Qt, b);

        double[][] arrayR = R.getArray();
        double[][] arrayB = B.getArray();

        for (int i = size - 1; i >= 0; i--) {
            double t = 0.0;
            for (int j = i + 1; j < size; j++) {
                t = t + (arrayR[i][j] * x[0][j]);
            }
            x[0][i] = (arrayB[i][0] - t) / arrayR[i][i];
        }**/
        return new Matrix(x);
    }
    /**
     * Returns solution x after solving the system by using givens rotation
     *
     * @param A matrix A
     * @param b matrix b
     * @return solution x
     */
    public static Vector solveG(Matrix A, Vector b) throws IllegalOperandException {
        int size = A.getHeight();
        double[] x = new double[size];

        qr_fact_givens.setMatrix(A);
        Matrix R = qr_fact_givens.getR();
        Matrix Q = qr_fact_givens.getQ();
        Matrix Qt = transpose(Q);
        Vector B = matrixVectorMultiply(Qt, b);

        double[][] arrayR = R.getArray();
        double[] arrayB = B.getArray();

        for (int i = size - 1; i >= 0; i--) {
            double t = 0.0;
            for (int j = i + 1; j < size; j++) {
                t = t + (arrayR[i][j] * x[j]);
            }
            x[i] = (arrayB[i] - t) / arrayR[i][i];
        }
        return new Vector(x);
    }


    public static void main(String[] args) throws Exception{
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        System.out.println("Press 1 to enter through terminal");
        System.out.println("Press 2 to enter through a file");
        Matrix a;
        Vector b;
        String inputTypeLine = input.nextLine();
        int inputType = Integer.parseInt(inputTypeLine);
        if (inputType == 1) {
            System.out.println("Please enter the matrix A.");
            System.out.println("Enter empty line to terminate!");
            a = input.readMatrix();
            System.out.println("Please enter the  Vector b");
            System.out.println("Separate vector components by "
                    + "using a space.");
            b = input.readVector();
        } else {
            System.out.println("Enter file path.");
            System.out.println("File must contain augmented matrix");
            String path = input.nextLine();
            File file = new File(path);
            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
            AugmentedMatrix augmat = fileInput.readAugmentedMatrixFromFile();
            a = augmat.getMatrix();
            b = augmat.getVector();
        }
        Vector x = solve_qr_b.solveG(a, b);
        System.out.println("The solution vector x = \n" + x.toString());
    }



}
