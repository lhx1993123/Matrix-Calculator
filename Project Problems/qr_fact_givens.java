import java.io.File;
import java.util.Scanner;
/**
    *This class represents the Givens Rotation Factorization of some matrix
    @author Hexiang Li
    @version 1.0
*/
public class qr_fact_givens extends LinearAlgebra {

    public static double[][] Gn;
    public static double[][] An;
    public static double[][] Q;

    /**
        *Do the Givens Rotation Operation
    */
    public static void rotation() throws IllegalOperandException {
        Gn = new double[An.length][An[0].length];
        Q = new double[An.length][An[0].length];

        int size = An.length;
        // initialize Gn and Q to be identity matrix
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (i == j){
                    Gn[i][j] = 1;
                    Q[i][j] = 1;
                } else{
                    Gn[i][j] = 0;
                    Q[i][j] = 0;
                }
            }
        }
        double a;
        double b;
        double cosX;
        double sinX;

        // Begins the rotation
        for (int i = 0; i < size; i++) {
            for (int j = (size - 1); j > i; j--) {
                a = An[j - 1][i];
                b = An[j][i];
                cosX = a / (Math.sqrt(a*a+b*b));
                sinX = - b / (Math.sqrt(a*a+b*b));

                Gn[j][j] = cosX;
                Gn[j][j - 1] = sinX;
                Gn[j - 1][j] = -sinX;
                Gn[j - 1][j - 1] = cosX;

                Matrix an = new Matrix(An);
                Matrix gn = new Matrix(Gn);
                an = matrixMultiply(gn, an);
                An = an.getArray();

                Matrix q = new Matrix(Q);
                q = matrixMultiply(gn, q);
                Q = q.getArray();

                // Change matrix Gn back to identity
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        if (k == l) {
                            Gn[k][l] = 1;
                        } else {
                            Gn[k][l] = 0;
                        }
                    }
                }
            }
        }
    }
    /**
        *Gets the Q matrix after rotation
        @return Matrix Q
    */
    public static Matrix getQ() throws IllegalOperandException {

        return LinearAlgebra.transpose(new Matrix(Q));
    }
    /**
        *Gets the R matrix after rotation
        @return Matrix R
    */
    public static Matrix getR() throws IllegalOperandException {

        return new Matrix(An);
    }

    /**
     * Sets matrix to be used
     * CALL THIS METHOD FIRST
     *
     */
    public static void setMatrix(Matrix m1) throws IllegalOperandException {
        An = m1.getArray();
        rotation();
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
        qr_fact_givens.setMatrix(m1);
        Matrix q = qr_fact_givens.getQ();
        Matrix r = qr_fact_givens.getR();
        System.out.println("Q = \t\n" + q.toString());
        System.out.println("R = \t\n" + r.toString());
        double error = LinearAlgebra.matrixMaxNorm(
                LinearAlgebra.matrixSubtract(LinearAlgebra.matrixMultiply(q, r)
                        , m1));
        System.out.println("Error = " + error);
        System.out.println("Q*R = \t\n" + LinearAlgebra.matrixMultiply(q, r));
    }
}
