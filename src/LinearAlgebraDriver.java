import java.util.InputMismatchException;
/**
 * Driver for Linear Algebra.
 *
 * @author Michael Maurer
 * @version 1.2
 */
public class LinearAlgebraDriver {

    /**
     * Runs program asking user for input and running linear algebra methods.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        boolean done = false;
        System.out.println("Howdy!");
        System.out.println("This heres the greatest calculator ever");
        System.out.println("created with 10 functions or less.");
        while (!done) {
            try {
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println("0. matrix + matrix");
                System.out.println("1. matrix * vector");
                System.out.println("2. vector . vector");
                System.out.println("3. vector + vector");
                System.out.println("4. matrix * matrix");
                System.out.println("5. transpose");
                System.out.println("6. determinant");
                System.out.println("7. trace");
                System.out.println("8. Gauss-Seidel solution");
                System.out.println("20. Exit\n");
                String line = input.nextLine();
                int userInput = Integer.parseInt(line);
                System.out.println();
                if (userInput == 0) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m2 = input.readMatrix();
                    System.out.println();
                    System.out.println(LinearAlgebra.matrixAdd(m1, m2));
                } else if (userInput == 1) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println("Please enter a vector!");
                    Vector v1 = input.readVector();
                    System.out.println();
                    System.out.println();
                    System.out.println(
                        LinearAlgebra.matrixVectorMultiply(m1, v1));
                } else if (userInput == 2) {
                    System.out.println("Please enter a vector!");
                    System.out.println("Separate vector components by "
                        + "using a space.");
                    Vector v1 = input.readVector();
                    System.out.println();
                    System.out.println("Please enter a vector!");
                    System.out.println("Separate vector components by "
                        + "using a space.");
                    Vector v2 = input.readVector();
                    System.out.println();
                    System.out.println();
                    System.out.println(LinearAlgebra.dotProduct(v1, v2));
                } else if (userInput == 3) {
                    System.out.println("Please enter a vector!");
                    System.out.println("Separate vector components by "
                        + "using a space.");
                    Vector v1 = input.readVector();
                    System.out.println();
                    System.out.println("Please enter a vector!");
                    System.out.println("Separate vector components by "
                        + "using a space.");
                    Vector v2 = input.readVector();
                    System.out.println();
                    System.out.println();
                    System.out.println(LinearAlgebra.vectorAdd(v1, v2));
                } else if (userInput == 4) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m2 = input.readMatrix();
                    System.out.println();
                    System.out.println(LinearAlgebra.matrixMultiply(m1, m2));
                } else if (userInput == 5) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println();
                    System.out.println(LinearAlgebra.transpose(m1));
                } else if (userInput == 6) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println();
                    System.out.println(LinearAlgebra.determinant(m1));
                } else if (userInput == 7) {
                    System.out.println("Please enter a matrix!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println();
                    System.out.println(LinearAlgebra.trace(m1));
                } else if (userInput == 8) {
                    System.out.println("Ax = b");
                    System.out.println("Please enter the matrix A!");
                    System.out.println("Enter empty line to terminate!");
                    Matrix m1 = input.readMatrix();
                    System.out.println();
                    System.out.println("Please enter the vector b!");
                    System.out.println("Separate vector components by "
                            + "using a space.");
                    Vector v1 = input.readVector();
                    System.out.println();
                    System.out.println("Please enter the number of iterations!");
                    int i = input.nextInt();
                    System.out.println(LinearAlgebra.gaussSeidel(m1, v1, i));
                } else if (userInput == 20) {
                    done = true;
                }
                input.nextLine(); //press any key to continue
            } catch (IllegalOperandException e) {
                System.out.println("Sorry, something went wrong.");
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}