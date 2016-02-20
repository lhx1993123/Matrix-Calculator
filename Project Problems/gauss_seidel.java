import java.io.File;

/**
 * Created by Henry on 3/20/15.
 */
public class gauss_seidel {

    public static Vector splitStream(Vector v) {
        double[] v0 = new double[v.getLength()/2];
        for (int i = 0; i < v.getLength(); i += 2 ) {
            v0[i/2] = v.get(i);
        }
        return new Vector(v0);
    }

    public static Vector decode(Matrix a, Vector b)
            throws IllegalOperandException {
        double tolerance = 1e-8;
        Vector solution = LinearAlgebra.gaussSeidel(a, b, tolerance);
        double[] modSolution = new double[solution.getLength()];
        for (int i = 0; i < modSolution.length; i++) {
            modSolution[i] = Math.abs(solution.get(i)) % 2;
        }
        return new Vector(modSolution);
    }

    public static void main(String[] args) throws Exception {
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        System.out.println("Press 1 to enter through terminal");
        System.out.println("Press 2 to enter through a file");
        Vector v;
        String inputTypeLine = input.nextLine();
        int inputType = Integer.parseInt(inputTypeLine);
        if (inputType == 1) {
            System.out.println("Please enter the  encoded binary stream");
            System.out.println("Separate components by "
                    + "using a space.");
            v = input.readVector();
        } else {
            System.out.println("Enter file path.");
            System.out.println("File may contain one line of elements " +
                    "separated by a space.");
            String path = input.nextLine();
            File file = new File(path);
            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
            v = fileInput.readVector();
        }
        Vector v0 = gauss_seidel.splitStream(v);
        Matrix A0 = ConvolutionalMatrix.getA0Matrix(v0);
        System.out.println("Decoding - please wait");
        Vector decoded = gauss_seidel.decode(A0, v0);
        System.out.println("The decoded binary stream is: \n" + decoded.toString());
    }
}
