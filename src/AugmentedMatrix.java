/**
 * Created by Henry on 3/28/15.
 */
public class AugmentedMatrix {
    private double[][] matrix;
    private double[] vector;

    public Matrix getMatrix() {
        return new Matrix(matrix);
    }

    public Vector getVector() {
        return new Vector(vector);
    }

    public void setMatrix(Matrix m) {
        this.matrix = m.getArray();
    }

    public void setVector(Vector v) {
        this.vector = v.getArray();
    }
}
