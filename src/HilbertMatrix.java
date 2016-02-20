import java.util.Scanner;
import java.math.BigDecimal;
/**
    *This class represents part A of the project
    @author Hexiang Li
    @version 1.0
*/
public class HilbertMatrix {
    /**
        *Gets the Hilbert Matrix
        @param n the size of Hilbert Matrix (n*n)
        @return Hilbert Matrix
    */
    public static Matrix getHMatrix(int n) {
        double[][] HMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double row = i + 1;
                double coln = j + 1;
                double value = 1 / (row + coln - 1);
                HMatrix[i][j] = value;
            }
        }
        Matrix a = new Matrix(HMatrix);
        return a;
    }
    /**
        *Gets the Matrix b
        @param n the size of b according to the size of Hilbert Matrix
        @return Matrix b
    */
    public static Vector getB(int n) {
        double[] b = new double[n];
        double pow = (double) n / 3;
        double value = Math.pow(0.1, pow);
        for (int i = 0; i < n; i++) {
            b[i] = value;
        }
        return new Vector(b);

    }
    /**
        *Build a Hilbert Matrix and applies LU factorization,
        *Householder reflections, and Givens Rotation.
        *Note, just input one number for the size (since row and coloumn number are the same)
    */
    public static void main(String[] args) throws IllegalOperandException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the size of Hilbert Matrix: ");
        int size = scan.nextInt();
        Matrix a = getHMatrix(size);
        Vector b = getB(size);
        System.out.println();
        System.out.println("H = \n" + a.toString());
        System.out.println("b = \n" + b.toString());
        System.out.println("------------------------------------------------");

        System.out.println("L = \n" + lu_fact.getLower(a , 0).toString());
        System.out.println("U = \n" + lu_fact.getUpper(a , 0).toString());
        System.out.println("------------------------------------------------");
/**
        System.out.println("QR with Householder reflections: ");
        System.out.println("Q = \n" + qr_fact_househ.getQ(a).toString());
        Matrix r = qr_fact_househ.getR(a);
        System.out.println("Q = \n" + r.toString());
        System.out.println("Xsol = " + solve_qr_b.solveH(a,b).toString());
        System.out.println("------------------------------------------------");

        System.out.println("QR with Givens rotations: ");
        System.out.println("Q = \n" + qr_fact_givens.getQ(a).toString());
        Matrix g = qr_fact_givens.getR(a);
        System.out.println("Q = \n" + r.toString());
        System.out.println("Xsol = " + solve_qr_b.solveG(a,b).toString());
        System.out.println("------------------------------------------------");**/
    }
}
