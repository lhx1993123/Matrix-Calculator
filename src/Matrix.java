import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Immutable matrix Class
 */
public final class Matrix {

    private final double[][] matrix;
    private final int height;
    private final int width;
    private final NumberFormat df;



    /**
     * Initialize instance variables
     * @param matrix 2D array representation of Matrix
     */
    public Matrix(double[][] matrix) {
        df = DecimalFormat.getInstance();

        height = matrix.length;
        width = matrix[0].length;
        this.matrix = matrix;
    }

    /**
     * Gets value located at specified row and column
     * @param i row
     * @param j column
     * @return double located at row i and column j in matrix
     */
    public double get(int i, int j) {
        if ((i < 0) || (i > height)
            || (j < 0) || (j > width)) {
            throw new MatrixIndexOutOfBoundsException(
                "The element you are looking for does not exist.");
        } else {
            return matrix[i][j];
        }
    }

    /**
     * Get's the height of the matrix.
     * @return number of rows in matrix
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get's the width of the matrix.
     * @return number of columns in matrix
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets String representation of matrix.
     * Columns separated by tabs, rows by new lines.
     * @return String representation of matrix.
     */
    public String toString() {
        df.setMinimumFractionDigits(6);
        df.setMaximumFractionDigits(8);
        df.setRoundingMode(RoundingMode.DOWN);

        String matrixString = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrixString +=  df.format(matrix[i][j]) + "\t";
            }
            matrixString += "\n";
        }
        return matrixString;
    }

    /**
     * Returns backing array
     * Used only for JUnit testing
     * @return backing array
     */
    public double[][] getArray() {
        return matrix;
    }

}

