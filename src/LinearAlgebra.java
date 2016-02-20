/**
 * This class contains the methods for the matrix and vector operations.
 *
 * @author Henry Chen
 * @version 1.0
 */
public class LinearAlgebra {

    /**
     * Multiplies a Vector and a Matrix
     *
     * @param m A matrix.
     * @param v A vector.
     * @return A vector representing the product of m and v.
     * @throws IllegalOperandException Thrown when the width of the matrix
     *                                 and the length of the vector are not equal.
     */
    public static Vector matrixVectorMultiply(Matrix m, Vector v)
            throws IllegalOperandException {
        if (!(m.getWidth() == v.getLength())) {
            throw new IllegalOperandException("Cannot multiply a matrix "
                    + "of width " + m.getWidth()
                    + " with a vector of length "
                    + v.getLength() + ".");


        }
        double[] product = new double[m.getHeight()];
        for (int i = 0; i < m.getHeight(); i++) {
            double addedProductSum = 0;
            for (int j = 0; j < m.getWidth(); j++) {
                double vectorElement = v.get(j);
                addedProductSum += (vectorElement * m.get(i, j));
                product[i] = addedProductSum;
            }
        }
        return new Vector(product);
    }

    /**
     * Adds two matricies
     *
     * @param m1 The first matrix
     * @param m2 The second matrix
     * @return The sum of the matricies
     * @throws IllegalOperandException Thrown when
     *                                 the height or width of the two matricies are not equal.
     */
    public static Matrix matrixAdd(Matrix m1, Matrix m2)
            throws IllegalOperandException {
        if (!(m1.getWidth() == m2.getWidth())) {
            throw new IllegalOperandException("Cannot add a matrix "
                    + "of width " + m1.getWidth()
                    + " and a matrix of width "
                    + m2.getWidth() + ".");
        } else if (!(m1.getHeight() == m2.getHeight())) {
            throw new IllegalOperandException("Cannot add a matrix "
                    + "of height " + m1.getHeight()
                    + " and a matrix of height "
                    + m2.getHeight() + ".");
        }
        double[][] sum = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m2.getWidth(); j++) {
                sum[i][j] = m1.get(i, j) + m2.get(i, j);
            }
        }
        return new Matrix(sum);
    }

    public static Matrix matrixSubtract(Matrix m1, Matrix m2)
            throws IllegalOperandException {
        if (!(m1.getWidth() == m2.getWidth())) {
            throw new IllegalOperandException("Cannot subtract a matrix "
                    + "of width " + m1.getWidth()
                    + " and a matrix of width "
                    + m2.getWidth() + ".");
        } else if (!(m1.getHeight() == m2.getHeight())) {
            throw new IllegalOperandException("Cannot subtract a matrix "
                    + "of height " + m1.getHeight()
                    + " and a matrix of height "
                    + m2.getHeight() + ".");
        }
        double[][] sub = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m2.getWidth(); j++) {
                sub[i][j] = m1.get(i, j) - m2.get(i, j);
            }
        }
        return new Matrix(sub);
    }

    /**
     * Multiplies a scalar and a matrix
     *
     * @param scalar scalar
     * @param m1     matrix
     * @return product
     */
    public static Matrix scalarMultiply(double scalar, Matrix m1) {
        double[][] product = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                product[i][j] = scalar * m1.get(i, j);
            }
        }
        return new Matrix(product);
    }

    /**
     * Multiplies a scalar and a vector
     *
     * @param scalar scalar
     * @param v1     vector
     * @return product
     */
    public static Vector scalarMultiply(double scalar, Vector v1) {
        double[] product = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            product[i] = scalar * v1.get(i);
        }
        return new Vector(product);
    }

    /**
     * Gets the magnitude of a vector
     *
     * @param v1 vector
     * @return magnitude
     */
    public static double magnitude(Vector v1) {
        double magnitude = 0;
        for (int i = 0; i < v1.getLength(); i++) {
            magnitude += Math.pow(v1.get(i), 2);
        }
        magnitude = Math.sqrt(magnitude);
        return magnitude;
    }

    /**
     * Normalizes a vector
     *
     * @param v1 vector
     * @return normalized vector
     */
    public static Vector normalize(Vector v1) {
        double magnitude = magnitude(v1);
        double[] normalized = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            normalized[i] = v1.get(i) / magnitude;
        }
        return new Vector(normalized);
    }

    /**
     * Finds the dot product of two vectors.
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The dot product of the two vectors
     * @throws IllegalOperandException Thrown when the
     *                                 length fo the two vectors are not equal.
     */
    public static double dotProduct(Vector v1, Vector v2)
            throws IllegalOperandException {
        if (!(v1.getLength() == v2.getLength())) {
            throw new IllegalOperandException("Cannot find the dot product "
                    + "of a vector of length "
                    + v1.getLength()
                    + " and a vector of length "
                    + v2.getLength() + ".");
        }
        double[] products = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            products[i] = v1.get(i) * v2.get(i);
        }
        double sum = 0;
        for (double product : products) {
            sum += product;
        }
        return sum;
    }

    /**
     * Adds two vectors
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The sum of the two vectors
     * @throws IllegalOperandException Thrown when the
     *                                 length of the two vectors are not equal.
     */
    public static Vector vectorAdd(Vector v1, Vector v2)
            throws IllegalOperandException {
        if (!(v1.getLength() == v2.getLength())) {
            throw new IllegalOperandException("Cannot add a vector "
                    + "of length " + v1.getLength()
                    + " and a vector of length " + v2.getLength() + ".");
        }
        double[] sums = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            sums[i] = v1.get(i) + v2.get(i);
        }
        return new Vector(sums);
    }

    /**
     * Subtracts two vectors
     *
     * @param v1 The first vector
     * @param v2 The second vector
     * @return The difference of the two vectors
     * @throws IllegalOperandException Thrown when the
     *                                 length of the two vectors are not equal.
     */
    public static Vector vectorSubtract(Vector v1, Vector v2)
            throws IllegalOperandException {
        if (!(v1.getLength() == v2.getLength())) {
            throw new IllegalOperandException("Cannot subtract a vector "
                    + "of length " + v1.getLength()
                    + " and a vector of length " + v2.getLength() + ".");
        }
        double[] subs = new double[v1.getLength()];
        for (int i = 0; i < v1.getLength(); i++) {
            subs[i] = v1.get(i) - v2.get(i);
        }
        return new Vector(subs);
    }

    /**
     * Multiplies two matricies
     *
     * @param m1 first matrix
     * @param m2 second matrix
     * @return product of two matricies
     * @throws IllegalOperandException if the two matricies cannot be multiplied
     */
    public static Matrix matrixMultiply(Matrix m1, Matrix m2)
            throws IllegalOperandException {
        if (m1.getWidth() != m2.getHeight()) {
            throw new IllegalOperandException("Cannot multiply a matrix "
                    + "of width " + m1.getWidth()
                    + " and a matrix of height "
                    + m2.getHeight() + ".");
        }

        double[][] product = new double[m1.getHeight()][m2.getWidth()];
        //fill product matrix with 0's
        for (int a = 0; a < m1.getHeight(); a++) {
            for (int b = 0; b < m2.getWidth(); b++) {
                product[a][b] = 0.0;
            }
        }
        for (int i = 0; i < m1.getHeight(); i++) { // m1 rows
            for (int j = 0; j < m2.getWidth(); j++) { // m2 cols
                for (int k = 0; k < m1.getWidth(); k++) { // m1 cols
                    product[i][j] += m1.get(i, k) * m2.get(k, j);
                }
            }
        }
        return new Matrix(product);
    }

    /**
     * Returns transpose of matrix
     *
     * @param m1 matrix
     * @return transpose of matrix
     */
    public static Matrix transpose(Matrix m1) {
        double[][] transpose = new double[m1.getWidth()][m1.getHeight()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                transpose[j][i] = m1.get(i, j);
            }
        }
        return new Matrix(transpose);
    }


    /**
     * Gets determinant of matrix by Laplace expansion.
     *
     * @param m1 Matrix
     * @return Determinant
     * @throws IllegalOperandException if matrix is not square
     */
    public static double determinant(Matrix m1)
            throws IllegalOperandException {
        if (m1.getHeight() != m1.getWidth()) {
            throw new IllegalOperandException("Cannot get determinant of a non-square matrix.");
        }
        int size = m1.getHeight();
        if (size == 1) {
            return m1.get(0, 0);
        } else if (size == 2) {
            return m1.get(0, 0) * m1.get(1, 1) - m1.get(0, 1) * m1.get(1, 0);
        } else {
            double determinant = 0;
            for (int j = 0; j < m1.getWidth(); j++) { // j = m1 column, i = m1 row, a = minor row , b = minor column
                double cofactor = m1.get(0, j);
                double[][] minorArray = new double[m1.getHeight() - 1][m1.getWidth() - 1];

                for (int a = 0; a < minorArray.length; a++) { //set values of minors

                    for (int b = 0, n = 0; b < minorArray[0].length; b++, n++) {
                        if ((n == j) && (b == n)) { //skip column of cofactor
                            b--;
                            continue;
                        }
                        minorArray[a][b] = m1.get(a + 1, n); // since first row is always skipped
                    }
                }
                Matrix minor = new Matrix(minorArray);
                determinant += Math.pow(-1, j) * cofactor * determinant(minor); //recursively get determinants of minors
            }
            return determinant;
        }
    }

    /**
     * Gets Trace of Matrix (diagonals added together)
     *
     * @param m1 matrix
     * @return trace
     * @throws IllegalOperandException if matrix is not square
     */
    public static double trace(Matrix m1)
            throws IllegalOperandException {
        if (m1.getHeight() != m1.getWidth()) {
            throw new IllegalOperandException("Cannot get trace of a non-square matrix.");
        }
        int trace = 0;
        for (int i = 0; i < m1.getHeight(); i++) {
            trace += m1.get(i, i);
        }
        return trace;
    }

    /**
     * Gets adjoint matrix
     *
     * @param m1 matrix
     * @return adjoint
     * @throws IllegalOperandException if matrix is not square
     */
    public static Matrix adjoint(Matrix m1)
            throws IllegalOperandException {
        int count = 0; // counter for -1 exponent
        double[][] adjoint = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) { // j = m1 column, i = m1 row, a = minor row , b = minor column
            for (int j = 0; j < m1.getWidth(); j++) {
                double[][] minorArray = new double[m1.getHeight() - 1][m1.getWidth() - 1];
                for (int a = 0, m = 0; a < minorArray.length; a++, m++) { //set values of minors
                    if ((m == i) && (a == m)) {
                        a--;
                        continue;
                    }
                    for (int b = 0, n = 0; b < minorArray[0].length; b++, n++) {
                        if ((n == j) && (b == n)) { //skip column of cofactor
                            b--;
                            continue;
                        }
                        minorArray[a][b] = m1.get(m, n); // since first row is always skipped
                    }
                }
                Matrix minor = new Matrix(minorArray);
                adjoint[i][j] = Math.pow(-1, count++) * determinant(minor);
            }

        }
        Matrix adjointMatrix = new Matrix(adjoint);
        return transpose(adjointMatrix);
    }

    /**
     * Gets inverse of matrix
     * Used only for getting iterations.
     * Not used for calculating anything.
     * @param m1 matrix
     * @return inverse
     * @throws IllegalOperandException if determinant is 0
     */
    public static Matrix inverse(Matrix m1)
            throws IllegalOperandException {
        double determinant = determinant(m1);
        if (determinant == 0) {
            throw new IllegalOperandException("Determinant is 0. Cannot take inverse.");
        }
        Matrix adjoint = adjoint(m1);
        return scalarMultiply(1 / determinant, adjoint);
    }

    /**
     * Returns matrix with only diagonals, everything else 0
     * used for GS iterations
     *
     * @param m1 matrix
     * @return diagonals
     */
    public static Matrix diagonal(Matrix m1) {
        double[][] diagonal = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                if (i == j) {
                    diagonal[i][j] = m1.get(i, j);
                }
            }
        }
        return new Matrix(diagonal);
    }

    /**
     * Returns lower portion of matrix, everything else 0
     * used for GS iterations
     *
     * @param m1 matrix
     * @return lower portion
     */
    public static Matrix lower(Matrix m1) {
        double[][] lower = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                if (i > j) {
                    lower[i][j] = m1.get(i, j);
                }
            }
        }
        return new Matrix(lower);
    }

    /**
     * Returns upper portion of matrix, everything else 0
     * used for GS iterations
     *
     * @param m1 matrix
     * @return upper portion
     */
    public static Matrix upper(Matrix m1) {
        double[][] upper = new double[m1.getHeight()][m1.getWidth()];
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                if (j > i) {
                    upper[i][j] = m1.get(i, j);
                }
            }
        }
        return new Matrix(upper);
    }

    /**
     * Returns the solution X through Gauss-Seidel iterations
     * Ax = b
     * A = s-t
     *
     * @param a A matrix
     * @param b b Vector
     * @param tolerance tolerance
     * @return solution
     */
    public static Vector gaussSeidel(Matrix a, Vector b, double tolerance)
            throws IllegalOperandException {
        //initial guess = 0
        double[] xArray = new double[b.getLength()];
        java.util.Arrays.fill(xArray, 0);
        Vector x = new Vector(xArray);
        Matrix s = matrixAdd(lower(a), diagonal(a));
        Matrix t = scalarMultiply(-1, upper(a));
        int iteration = 1;
        do {
            x = lowerSolve(s, vectorAdd(matrixVectorMultiply(t, x), b));
            //System.out.println(x.toString());
            iteration++;
        } while (vectorMaxNorm(vectorSubtract(matrixVectorMultiply(a, x), b)) > tolerance);

        System.out.println(iteration + " iterations used to achieve tolerance " + tolerance + ".");
        return x;
    }

    /**
     * Returns number of iterations needed for the Gauss-Seidel method
     * to reach a certain level of accuracy
     * @param a Matrix used
     * @param tolerance tolerance level
     * @return number of iterations
     * @throws IllegalOperandException
     *
    public static int getGaussSeidelMinIterations(Matrix a, double tolerance)
            throws IllegalOperandException {
        Matrix s = matrixAdd(lower(a), diagonal(a));
        Matrix t = scalarMultiply(-1, upper(a));
        int eigiterations = getEigenvalueIterations(matrixMultiply(inverse(s), t), tolerance);
        double eig = largestEigenvalue(matrixMultiply(inverse(s), t), eigiterations);
        System.out.println(matrixMultiply(inverse(s), t));
        if (eig == 0) {
            return 1;
        }
        if (eig > 1) {
            throw new IllegalOperandException("Method does not converge.");
        }
        int iterations = (int) Math.ceil(Math.abs(Math.log(tolerance) / Math.log(eig)));
        if (iterations > 500) {
            throw new IllegalOperandException("Matrix does not converge");
        }
        return iterations;
    }
    **/

    /**
     * Returns the solution X through Jacobi iterations
     * Ax = b
     * A = s-t
     *
     * @param a A matrix
     * @param b b Vector
     * @param tolerance tolerance
     * @return solution
     */
    public static Vector jacobi(Matrix a, Vector b, double tolerance)
            throws IllegalOperandException {
        //initial guess = 0
        double[] xArray = new double[b.getLength()];
        java.util.Arrays.fill(xArray, 0);
        Vector x = new Vector(xArray);
        Matrix s = diagonal(a);
        Matrix t = matrixAdd(scalarMultiply(-1, lower(a)), scalarMultiply(-1, upper(a)));
        int iteration = 1;
        do {
            x = lu_fact.solveUXY(s, vectorAdd(matrixVectorMultiply(t, x), b));
            iteration++;
        } while (vectorMaxNorm(vectorSubtract(matrixVectorMultiply(a, x), b)) > tolerance);
        System.out.println(iteration + " iterations used to achieve tolerance " + tolerance + ".");
        return x;
    }

    /**
     * Returns the number of iterations needed for the Jacobi method
     * to reach a certain tolerance
     * @param a Matrix
     * @param tolerance tolerance needed
     * @return number of iterations
     * @throws IllegalOperandException
     *
    public static int getJacobiMinIterations(Matrix a, double tolerance)
            throws IllegalOperandException {
        Matrix s = diagonal(a);
        Matrix t = matrixAdd(scalarMultiply(-1, lower(a)), scalarMultiply(-1, upper(a)));
        int eigiterations = getEigenvalueIterations(matrixMultiply(inverse(s), t), tolerance);
        double eig = largestEigenvalue(matrixMultiply(inverse(s), t), eigiterations);
        System.out.println(matrixMultiply(inverse(s), t));
        if (eig == 0) {
            return 1;
        }
        if (eig > 1) {
            throw new IllegalOperandException("Method does not converge.");
        }
        System.out.println("Eigit = " + eigiterations);
        System.out.println("Eig  =" + eig);
        int iterations = (int) Math.ceil(Math.abs(Math.log(tolerance) / Math.log(eig)));
        if (iterations > 500) {
            throw new IllegalOperandException("Matrix does not converge");
        }
        return iterations;
    }
    */

    /**
     * Returns the largest eigenvalue
     * Uses the power method
     *
     * @param a     Matrix a
     * @param i     number of iterations
     * @param guess initial guess
     * @return largest eigenvalue
     * @throws IllegalOperandException
     */
    public static double largestEigenvalue(Matrix a, int i, Vector guess)
            throws IllegalOperandException {
        Vector eigenvector = largestEigenvectorUnscaled(a, i, guess);
        return eigenvector.get(0);
    }

    /**
     * Returns largest eigenvalue using default guess [1 1 1 ...]
     * @param a Matrix a
     * @param i number of iterations
     * @return largest eigenvalue
     * @throws IllegalOperandException
     */
    public static double largestEigenvalue(Matrix a, int i)
            throws IllegalOperandException {
        double[] initialGuess = new double[a.getHeight()]; // guess will be [1 1 1 ...]
        for (int j = 0; j < initialGuess.length; j++) {
            initialGuess[j] = 3;
        }
        Vector guess = new Vector(initialGuess);
        Vector eigenvector = largestEigenvectorUnscaled(a, i, guess);
        return eigenvector.get(0);
    }

    /**
     * Returns the largest Eigenvector with default guess [1 0 0 ...]
     * Uses the power method
     *
     * @param a Matrix a
     * @param i number of iterations
     * @return eigenvector
     * @throws IllegalOperandException
     */
    public static Vector largestEigenvector(Matrix a, int i)
            throws IllegalOperandException {
        double[] initialGuess = new double[a.getHeight()]; // guess will be [1 1 1 ...]
        for (int j = 0; j < initialGuess.length; j++) {
            initialGuess[j] = 1;
        }
        Vector guess = new Vector(initialGuess);
        return largestEigenvector(a, i, guess);
    }

    /**
     * Returns the largest unscaled eigenvector
     * Private method - call largestEigenvector instead
     * Uses the power method
     *
     * @param a     Matrix a
     * @param i     number of iterations
     * @param guess initial guess
     * @return largest eigenvector
     * @throws IllegalOperandException
     */
    public static Vector largestEigenvectorUnscaled(Matrix a, int i, Vector guess)
            throws IllegalOperandException {
        Vector u = guess;
        int iteration = 0;
        while (iteration < i) {
            u = scalarMultiply(1 / u.get(0), matrixVectorMultiply(a, u));
            iteration++;
        }
        return u;
    }

    /**
     * Gets number of power-method iterations needed to
     * achieve a certain level of accuracy
     * @param m Matrix used
     * @param tolerance tolerance level
     * @return number of iterations
     * @throws IllegalOperandException
     */
    public static int getEigenvalueIterations(Matrix m, double tolerance)
            throws IllegalOperandException {
        int i = 3;
        double value = largestEigenvalue(m , 1);
        double cur = largestEigenvalue(m, 2);
        while (Math.abs(cur - value) > tolerance) {
            value = cur;
            cur = largestEigenvalue(m , i);
            i++;
        }
        return i;
    }

    /**
     * Returns the largest eigenvector
     * @param a     Matrix a
     * @param i     number of iterations
     * @param guess initial guess
     * @return largest eigenvector
     * @throws IllegalOperandException
     */
    public static Vector largestEigenvector(Matrix a, int i, Vector guess)
            throws IllegalOperandException {
        Vector eigenvector = largestEigenvectorUnscaled(a, i, guess);
        eigenvector = scalarMultiply(1/eigenvector.get(0), eigenvector);
        return eigenvector;
    }



    /**
     * Gets the norm of a matrix
     * @param m1 Matrix
     * @return norm
     * @throws IllegalOperandException
     */
    public static double matrixNorm(Matrix m1)
            throws IllegalOperandException {
        Matrix AAt = matrixMultiply(m1, transpose(m1));
        double eigenvalue = largestEigenvalue(AAt, 1);
        return Math.sqrt(Math.abs(eigenvalue));
    }

    public static double conditionNumber(Matrix m1)
            throws IllegalOperandException {
        return matrixNorm(m1) * matrixNorm(inverse(m1));
    }

    /**
     * Returns the max (infinity) norm of a matrix
     * @param m1 matrix
     * @return max norm
     */
    public static double matrixMaxNorm(Matrix m1) {
        double max = 0;
        for (int i = 0; i < m1.getHeight(); i++) {
            for (int j = 0; j < m1.getWidth(); j++) {
                if (Math.abs(m1.get(i,j)) > max) {
                    max = m1.get(i,j);
                }
            }
        }
        return Math.abs(max);
    }

    public static double vectorMaxNorm(Vector v1) {
        double max = 0;
        for (int i = 0; i < v1.getLength(); i++) {
            if (Math.abs(v1.get(i)) > max) {
                max = v1.get(i);
            }
        }

        return Math.abs(max);
    }

    private static Vector lowerSolve(Matrix l , Vector b) {
        double[] y = new double[b.getLength()];
        for(int z = 0; z < b.getLength(); z++) {
            y[z] = b.get(z);
        }
        for(int a = 0; a < l.getHeight(); a++) {
            for(int c = 0; c < l.getWidth(); c++) {
                double pivot = l.get(a,a);
                if(a > c) {
                    y[a] -= l.get(a,c) * y[c];
                } else if (a == c) {
                    y[a] /= pivot;
                }
            }
        }
        Vector yVec = new Vector(y);
        //System.out.println("your y vector is: " + yVec.toString());
        return yVec;
    }


}