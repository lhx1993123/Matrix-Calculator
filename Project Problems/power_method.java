/**
 * this class represents all of the matematical portions of part 3 of the computer project
 * @author Shantanu Mantri and Henry Chen on 3/22/2015
*/

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class power_method extends LinearAlgebra {
    private static final double[][] LESLIEARRAY = {
                            {0, 1.2, 1.1, .9, .1, 0, 0, 0, 0},
                            {.7, 0, 0, 0, 0, 0, 0, 0, 0},
                            {0, .85, 0, 0, 0, 0, 0, 0, 0},
                            {0, 0, .9, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, .9, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, .88, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, .8, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, .77, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0, .40, 0}
                            };
    public static final Matrix LESLIEMATRIX = new Matrix(LESLIEARRAY);
    private static final double[] pop = {2.1,1.9,1.8,2.1,2.0,1.7,1.2,0.9,0.5};
    public static final Vector INITIALPOP = LinearAlgebra.scalarMultiply(10e5, new Vector(pop));

    /**
     * gets the population for each year in normal conditions
     * @param initial Matrix which is the Leslie Matrix
     * @param distribution Vector which is the initial population distribution
    */
    public static void getPopulation(Matrix initial , Vector distribution) {
        //gets population from year 2010 to 2050 //
        NumberFormat df = DecimalFormat.getInstance();
        int year = 2000;
        double popOld = 0;
        double popNew = 0;
        double popInit = 0;
        //gets the initial population//
        for(int t = 0; t < distribution.getLength(); t++) {
            popInit += distribution.get(t);
        }
        System.out.println("The population in " + year + " is: " + popInit);
        double popChange;
        popOld = popInit;
        Vector current = distribution;
        //iterates using power method//
        for(int x = 0; x < 5; x++) {
            year += 10;
            try {
                current = matrixVectorMultiply(initial,current);
            }
            catch(IllegalOperandException e) {
                e.getMessage();
            }
            for(int y = 0; y < current.getLength(); y++) {
                popNew += current.get(y);
            }
            popChange = (popNew - popOld);
            df.setMinimumFractionDigits(5);
            df.setMaximumFractionDigits(5);
            df.setRoundingMode(RoundingMode.DOWN);
            System.out.println("The population in " + year + " will be " + popNew );
            double popChangeFrac = popChange/popOld;
            System.out.println("The population change in " + year + " will be " + df.format(popChangeFrac));
            popOld = popNew;
        }
    }
    /**
     * This method helps to create a half identity matrix where the first row has values 0.5 instead of 1 or 0
     * Used in the adjusted population situation
     * @param x Matrix that can be any matrix with the size you need the identity matrix to be
     * @return Matrix half-identity matrix
     */
    private static Matrix getHalfIdentity(Matrix x) {
        int size = x.getHeight();
        double[][] ident = new double[size][size];
        for(int p = 0; p < size; p++) {
            for(int y = 0; y < size; y++) {
                if(y == 0) {
                    ident[p][y] = 0.5;
                }
                else if(p == y) {
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
     * gets adjusted population where birth rate is changed in the year 2020
     * @param initial Matrix same as getpopulation
     * @param distribution Vector same as in getpopulation
    */
    public static void getAdjustedPopulation(Matrix initial , Vector distribution) {
        NumberFormat df = DecimalFormat.getInstance();

        //gets population from year 2010 to 2050 //
        int year = 2000;
        double popOld = 0;
        double popNew = 0;
        double popInit = 0;

        //gets the initial population//
        for(int t = 0; t < distribution.getLength(); t++) {
            popInit += distribution.get(t);
        }
        System.out.println("The population in " + year + " is: " + popInit);
        double popChange;
        popOld = popInit;
        Vector current = distribution;
        //iterates using power method//
        for(int x = 0; x < 5; x++) {
            if(year == 2020) {
                for(int s = 0; s < initial.getWidth(); s++) {
                    try{
                        initial = matrixMultiply(initial, getHalfIdentity(initial));
                    }
                    catch(IllegalOperandException f) {
                        f.getMessage();
                    }
                }
            }
            year += 10;
            try {
                current = matrixVectorMultiply(initial,current);
            }
            catch(IllegalOperandException e) {
                e.getMessage();
            }
            for(int y = 0; y < current.getLength(); y++) {
                popNew += current.get(y);
            }
            df.setMinimumFractionDigits(5);
            df.setMaximumFractionDigits(5);
            df.setRoundingMode(RoundingMode.DOWN);
            popChange = (popNew - popOld);
            System.out.println("The population in " + year + " will be " + popNew );
            double popChangeFrac = popChange/popOld;
            System.out.println("The population change in " + year + " will be " + df.format(popChangeFrac));
            popOld = popNew;
        }
    }
    /**
     * gets the most precise eigenvalue (and largest) for the Matrix
     * @param mat Matrix which is your Leslie Matrix
    */
    public static void getPreciseEigenValue(Matrix mat) {
        NumberFormat df = DecimalFormat.getInstance();
        df.setMinimumFractionDigits(8);
        df.setMaximumFractionDigits(8);
        df.setRoundingMode(RoundingMode.DOWN);
        int i = 2;
        try {
            double value = largestEigenvalue(mat , 1);
            while(true) {
                double cur = largestEigenvalue(mat , i);
                if (Math.abs(cur - value) < 0.00000001) {
                    System.out.println("Eigenvalue is: " + df.format(cur));
                    break;
                }
                i++;
                value = cur;
            }
        }
        catch(IllegalOperandException e) {
            e.getMessage();
        }
    }
    /**
     * uses a scanner to get the matrix and apply all the methods above
     * @param args String[]
    */
    public static void main(String[] args) throws Exception{
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        System.out.println("Press 1 to enter matrix through terminal");
        System.out.println("Press 2 to enter matrix through a file");
        Matrix m1;
        Vector guess;
        String inputTypeLine = input.nextLine();
        int inputType = Integer.parseInt(inputTypeLine);
        if (inputType == 1) {
            System.out.println("Please enter a matrix.");
            System.out.println("Enter empty line to terminate!");
            m1 = input.readMatrix();
            System.out.println("Please enter the initial approximation vector.");
            System.out.println("Separate vector components by "
                    + "using a space.");
            guess = input.readVector();
        } else {
            System.out.println("Enter file path for the matrix.");
            String path = input.nextLine();
            File file = new File(path);
            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
            m1 = fileInput.readMatrixFromFile();
            System.out.println("Enter file path for the initial guess");
            String path1 = input.nextLine();
            File file1 = new File(path1);
            LinearAlgebraScanner fileInput1 = new LinearAlgebraScanner(file1);
            guess = fileInput1.readVector();

        }
        double tol;
        System.out.println("Please enter the error tolerance.");
        tol = input.nextDouble();
        int eigIterations = LinearAlgebra.getEigenvalueIterations(m1, tol);
        double eigenvalue = LinearAlgebra.largestEigenvalue(m1, eigIterations, guess);
        Vector eigenvector = LinearAlgebra.largestEigenvector(m1, eigIterations, guess);
        System.out.println(eigIterations
                + " iterations were used to achieve an error tolerance of " + tol);
        System.out.println("The largest eigenvector is: \n" + eigenvector);
        System.out.println("The largest eigenvalue is: \n" + eigenvalue);
    }
}
