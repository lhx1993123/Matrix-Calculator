import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Immutable abstraction for Vector
 *
 * @author Michael Maurer
 * @version 1.2
 */
public final class Vector {

    private final double[] vector;
    private final int length;
    private final NumberFormat df;

    /**
     * Initialize instance variables
     * @param vector array representation of vector
     */
    public Vector(double[] vector) {
        df = DecimalFormat.getInstance();
        this.vector = vector;
        length = vector.length;
    }

    /**
     * Gets value located at specified index
     * @param i index in vector
     * @return double located at index 'i' in vector
     */
    public double get(int i) {
        if ((i < 0) || (i > length)) {
            throw new VectorIndexOutOfBoundsException(
                "The element you are looking for does not exist.");
        } else {
            return vector[i];
        }
    }

    /**
     * Gets the length of the Vector.
     * @return number of components in vector
     */
    public int getLength() {
        return length;
    }

    /**
     * String representation of vector with components
     * separated by tabs
     * @return String representation of vector
     */
    public String toString() {
        df.setMinimumFractionDigits(8);
        df.setMaximumFractionDigits(8);
        df.setRoundingMode(RoundingMode.DOWN);
        String vectorString = "";
        for (int i = 0; i < vector.length; i++) {
            vectorString += df.format(vector[i]) + "\t";
        }
        return vectorString;
    }

    /**
     * Returns backing array
     * Used only for JUnit testing
     * @return backing array
     */
    public double[] getArray() {
        return vector;
    }
}