/**
 * Created by Henry on 3/20/15.
 * Static Methods for Convolutional Code Problem
 */
public class ConvolutionalMatrix {


    public static Matrix getA0Matrix(Vector v1) {
        //encoder: xj + xj-2 + xj-3
        double[] u0 = {1, 0, 1, 1};
        double[][] A0 = new double[v1.getLength()][v1.getLength()];
        for (int i = 0; i < A0.length; i++) {
            if (i < v1.getLength() ) {
                int j = 0;
                while (j < i) {
                    A0[i][j] = 0;
                    j++;
                }
                for (int k = 0; k < u0.length; k++) {
                    if (j < A0[0].length) {
                        A0[i][j] = u0[k];
                        j++;
                    }
                }
                while (j < A0[0].length) {
                    A0[i][j] = 0;
                    j++;
                }
            }
        }
        return LinearAlgebra.transpose(new Matrix(A0));
    }

    public static Matrix getA1Matrix(Vector v1) {
        //encoder: xj + xj-2 + xj-3
        double[] u1 = {1, 1, 0, 1};
        double[][] A1 = new double[v1.getLength()][v1.getLength()];
        for (int i = 0; i < A1.length; i++) {
            if (i < v1.getLength() - 3) {
                int j = 0;
                while (j < i) {
                    A1[i][j] = 0;
                    j++;
                }
                for (double element : u1) {
                    A1[i][j] = element;
                    j++;
                }
                while (j < A1[0].length) {
                    A1[i][j] = 0;
                    j++;
                }
            } else {
                for (int l = 0; l < A1[0].length; l++) {
                    A1[i][l] = 0;
                }
            }
        }
        return LinearAlgebra.transpose(new Matrix(A1));
    }

    public static Vector encode(Vector v)
            throws IllegalOperandException {

        Vector y0 = matrixVectorMultiplyBinary(LinearAlgebra.transpose(getA0Matrix(v)), v);
        Vector y1 = matrixVectorMultiplyBinary(LinearAlgebra.transpose(getA1Matrix(v)), v);
        double[] y = new double[y0.getLength() * 2];
        for (int i = 0; i < y.length; i++) {
            if (i % 2 == 0) {
                y[i] = y0.get(i/2);
            } else {
                y[i] = y1.get(i/2);
            }
        }

        return new Vector(y);

    }

    public static Vector matrixVectorMultiplyBinary(Matrix m, Vector v)
            throws IllegalOperandException {
        Vector notBinary = LinearAlgebra.matrixVectorMultiply(m, v);
        double[] ans = new double[notBinary.getLength()];

        for (int i = 0; i < notBinary.getLength(); i++) {
            ans[i] = notBinary.get(i) % 2;
        }
        return new Vector(ans);
    }
}
