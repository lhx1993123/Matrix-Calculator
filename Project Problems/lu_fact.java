import java.io.File;
import java.util.Scanner;
/**
    *This class represents the LU Factorization of some matrix
    * If you guys see any errors (I've tested it for 1x1 all the way to 5 x 5 matrixes) tell me or fix it immediately
    *[IMPORTANT]: I haven't calculated the error yet
    *We need the eigenvalue method to get the error!
    @author Shantanu Mantri on 3/17/2015
    @version 1.0
*/
public class lu_fact extends LinearAlgebra {
    /**
        *Gets the transformation matrix for gaussian elimination
        @param mat Matrix, initial matrix
        @param t int tells us what transformation we're on (starting at 0)
        @return Matrix newMat which will become U when eventually row reduced
    */
    public static Matrix getMult(Matrix mat, int t) {
        if(t >= mat.getHeight()) {
            return mat;
        }
        double[][] lower = new double[mat.getWidth()][mat.getHeight()];
        for(int i = 0; i < mat.getWidth(); i++) {
            for(int j = 0; j < mat.getHeight(); j++) {
                double pivot = mat.get(t,t);
                if (pivot == 0) {
                    return getIdentity(mat);
                } else if (i == j) {
                    lower[j][i] = 1;
                } else if (j > i && i == t) {
                    lower[j][i] = (-1*(mat.get(j,i)/pivot));
                } else
                    lower[j][i] = 0;
                }
            }
        Matrix newMat = new Matrix(lower);
        return newMat;
    }
    /**
        *This gets an identity matrix by taking the size of a matrix you put in
        @param x Matrix just to get the size
        @return Matrix identity if needed!
    */
    private static Matrix getIdentity(Matrix x) {
        int size = x.getHeight();
        double[][] ident = new double[size][size];
        for(int p = 0; p < size; p++) {
            for(int y = 0; y < size; y++) {
                if(p ==y) {
                    ident[p][y] = 1;
                }
                else {
                    ident[p][y] = 0;
                }
            }
        }
        Matrix identity = new Matrix(ident);
        return identity;
    }
    /**
        *Gets the upper matrix by multiplying matrices
        *performs the actual gaussian elimination
        @param m Matrix that is our initial matrix
        @param times integer that tells us how many iterations have been done
        @return Matrix next which gives us the fully reduced Upper matrix
    */
    public static Matrix getUpper(Matrix m , int times) {
        try {
            Matrix next = matrixMultiply(getMult(m , times),m);
            while(times != m.getHeight() - 2) {
                    times++;
                    next = matrixMultiply(getMult(next , times),next);
                }
            return next;
            }
        catch(IllegalOperandException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    /**
        *Gets the lower matrix by multiplying matrices
        *adds transformation matrixes to get our Lower matrix
        @param m Matrix that is our initial matrix
        @param times integer that tells us how many iterations have been done
        @return Matrix next which gives us the fully added Lower matrix
    */
    public static Matrix getLower(Matrix m , int times) {
        try {
            Matrix next = matrixMultiply(getMult(m , times),m);
            Matrix add = getMult(m , times);
            while(times != m.getHeight() - 2) {
                    times++;
                    add = matrixAdd(add , getMult(next , times));
                    next = matrixMultiply(getMult(next , times),next);
                }
            double[][] finalL = new double[m.getWidth()][m.getHeight()];
            for(int i = 0; i < m.getHeight(); i++) {
                for(int j = 0; j < m.getWidth(); j++){
                    if(i == j) {
                        finalL[i][j] = add.get(i,j) / (m.getHeight() - 1);
                    }
                    else {
                        if (add.get(i,j) != 0)
                            finalL[i][j] = -1*add.get(i,j);
                        else
                            finalL[i][j] = 0;
                    }
                }
            }
            Matrix lowerM = new Matrix(finalL);
            return lowerM;
            }
        catch(IllegalOperandException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    public static Vector solveLYB(Matrix l , Vector b) {
        double[] y = new double[b.getLength()];
        for(int z = 0; z < b.getLength(); z++) {
            y[z] = b.get(z);
        }
        for(int a = 0; a < l.getWidth(); a++) {
            for(int c = 0; c < l.getHeight(); c++) {
                double pivot = l.get(a,a);
                if(a < c) {
                    double factor = -1*(l.get(c,a)/pivot);
                    //System.out.println(factor);
                    y[c] += factor*y[a];
                }
            }
        }
        Vector yVec = new Vector(y);
        //System.out.println("your y vector is: " + yVec.toString());
        return yVec;
    }
    public static Vector solveUXY(Matrix u, Vector y) {
        double[] x = new double[y.getLength()];
        for(int z = 0; z < y.getLength(); z++) {
            x[z] = y.get(z);
        }
        for (int i = u.getWidth() - 1; i >= 0; i--) {
            double t = 0.0;
            for (int j = i + 1; j < u.getHeight(); j++) {
                t = t + (u.get(i,j) * x[j]);
            }
            x[i] = (y.get(i) - t) / u.get(i,i);
        }
        Vector xVec = new Vector(x);
        //System.out.println("your x vector is: " + xVec.toString());
        return xVec;
    }


    /**
     * Solves a system of equations Ax=b
     * @param a Matrix
     * @param b Vector
     * @return solution Vector
     */
    public static Vector solveSystem(Matrix a, Vector b) {
        Matrix l = getLower(a , 0);
        Matrix u = getUpper(a , 0);
        Vector y = solveLYB(l,b);
        return solveUXY(u,y);
    }
    /**
        *Scans matrix input and applies LU Factorization to the inputted matrix
        *Note, matrix must be entered in space to separate eg. 1 2 3 4
        *Pressing enter will create a new row
        @param args String[] that holds the matrix in a string format
    */
    public static void main(String[] args) throws Exception{
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
        System.out.println("L = \t\n" + lu_fact.getLower(m1 , 0).toString());
        System.out.println("U = \t\n" + lu_fact.getUpper(m1 , 0).toString());
        System.out.println();

    }
}
