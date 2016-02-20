import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.io.File;

/**
 * Created by Henry on 3/23/2015.
 */
public class ProjectDriver {

    public static void main(String[] args) {
        LinearAlgebraScanner input = new LinearAlgebraScanner();
        boolean done = false;
        System.out.println("Math2605 Project");
        while (!done) {
            try {
                System.out.println();
                System.out.println("What would you like to do?");
                System.out.println("0. Get LU Decomposition (1a)");
                System.out.println("1. Get QR Factorization - Householder (1b)");
                System.out.println("2. Get QR Factorization - Givens  (1b)");
                System.out.println("3. Solve Ax=b using LU (1c)");
                System.out.println("4. Solve Ax=b using QR (1c)");
                System.out.println("5. Solve Hx=b for n = [2,20] (1d)");
                System.out.println("6. Encode binary input stream (2)");
                System.out.println("7. Decode using Jacobi (2)");
                System.out.println("8. Decode using Gauss-Seidel (2)");
                System.out.println("9. Solve Ax=b using Jacobi");
                System.out.println("10. Solve Ax=b using Gauss-Seidel");
                System.out.println("11. Use power-method (3)");
                System.out.println("12. Get population from [2010, 2050]");
                System.out.println("13. Get largest eigenvalue of Leslie matrix");
                System.out.println("14. Get population/eigenvalue when birth rate is decreased");
                System.out.println("20. Exit\n");
                String line = input.nextLine();
                int userInput = Integer.parseInt(line);
                System.out.println();

                //LU Decomposition
                if (userInput == 0) {
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

                //QR Factorization (Householder)
                else if (userInput == 1) {
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
                    qr_fact_househ.setMatrix(m1);
                    Matrix q = qr_fact_househ.getQ();
                    Matrix r = qr_fact_househ.getR();
                    System.out.println("Q = \t\n" + q.toString());
                    System.out.println("R = \t\n" + r.toString());
                    double error = LinearAlgebra.matrixMaxNorm(
                            LinearAlgebra.matrixSubtract(LinearAlgebra.matrixMultiply(q, r)
                                    , m1));
                    System.out.println("Error = " + error);
                    System.out.println("Q*R = \t\n" + LinearAlgebra.matrixMultiply(q, r));
                }

                //QR Factorization (Givens)
                else if (userInput == 2) {
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
                    qr_fact_givens.setMatrix(m1);
                    Matrix q = qr_fact_givens.getQ();
                    Matrix r = qr_fact_givens.getR();
                    System.out.println("Q = \t\n" + q.toString());
                    System.out.println("R = \t\n" + r.toString());
                    double error = LinearAlgebra.matrixMaxNorm(
                            LinearAlgebra.matrixSubtract(LinearAlgebra.matrixMultiply(q, r)
                                    , m1));
                    System.out.println("Error = " + error);
                    System.out.println("Q*R = \t\n" + LinearAlgebra.matrixMultiply(q, r));
                }

                //Solve Ax=b using LU
                else if (userInput == 3) {
                    System.out.println("Press 1 to enter through terminal");
                    System.out.println("Press 2 to enter through a file");
                    Matrix a;
                    Vector b;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        System.out.println("Please enter the matrix A.");
                        System.out.println("Enter empty line to terminate!");
                        a = input.readMatrix();
                        System.out.println("Please enter the  Vector b");
                        System.out.println("Separate vector components by "
                                + "using a space.");
                        b = input.readVector();
                    } else {
                        System.out.println("Enter file path.");
                        System.out.println("File must contain augmented matrix");
                        String path = input.nextLine();
                        File file = new File(path);
                        LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                        AugmentedMatrix augmat = fileInput.readAugmentedMatrixFromFile();
                        a = augmat.getMatrix();
                        b = augmat.getVector();
                    }
                    Vector x = lu_fact.solveSystem(a, b);
                    System.out.println("The solution vector x = \n" + x.toString());
                }

                //Solve Ax=b using QR
                else if (userInput == 4) {
                    System.out.println("Press 1 to enter through terminal");
                    System.out.println("Press 2 to enter through a file");
                    Matrix a;
                    Vector b;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        System.out.println("Please enter the matrix A.");
                        System.out.println("Enter empty line to terminate!");
                        a = input.readMatrix();
                        System.out.println("Please enter the  Vector b");
                        System.out.println("Separate vector components by "
                                + "using a space.");
                        b = input.readVector();
                    } else {
                        System.out.println("Enter file path.");
                        System.out.println("File must contain augmented matrix");
                        String path = input.nextLine();
                        File file = new File(path);
                        LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                        AugmentedMatrix augmat = fileInput.readAugmentedMatrixFromFile();
                        a = augmat.getMatrix();
                        b = augmat.getVector();
                    }
                    Vector x = solve_qr_b.solveG(a, b);
                    System.out.println("The solution vector x = \n" + x.toString());
                }



                //Solve Hx=b for 2 through 20
                else if (userInput == 5) {
                    for (int size = 2; size <= 20; size++) {
                        Matrix hilbert = HilbertMatrix.getHMatrix(size);
                        Vector b = HilbertMatrix.getB(size);
                        Vector xQR = solve_qr_b.solveG(hilbert, b);
                        //Vector xLU = lu_fact.solveSystem(hilbert, b);
                        qr_fact_givens.setMatrix(hilbert);
                        double QRError = LinearAlgebra.matrixMaxNorm(
                                LinearAlgebra.matrixSubtract(
                                        LinearAlgebra.matrixMultiply(qr_fact_givens.getQ()
                                                ,qr_fact_givens.getR())
                                        , hilbert));
                        double LUError = LinearAlgebra.matrixMaxNorm(
                                LinearAlgebra.matrixSubtract(
                                        LinearAlgebra.matrixMultiply(lu_fact.getLower(hilbert, 0)
                                                ,lu_fact.getUpper(hilbert, 0))
                                        , hilbert));
                        double SolError = LinearAlgebra.vectorMaxNorm(
                                LinearAlgebra.vectorSubtract(
                                        LinearAlgebra.matrixVectorMultiply(hilbert
                                                , xQR), b));
                        System.out.println("The solution vector x when n = " + size + " is");
                        System.out.println(xQR.toString());
                        System.out.println("The error ||LU - H||: \t" + LUError);
                        System.out.println("The error ||QR - H||: \t" + QRError);
                        System.out.println("The error ||Hx - b||: \t" + SolError);
                        System.out.println();
                    }
                }

                //Encode binary input stream
                else if (userInput == 6) {
                    System.out.println("Press 1 to enter through terminal");
                    System.out.println("Press 2 to enter through a file");
                    Vector v1;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        System.out.println("Please enter the  binary stream");
                        System.out.println("Separate components by "
                                + "using a space.");
                        v1 = input.readVector();
                    } else {
                        System.out.println("Enter file path.");
                        System.out.println("File may contain one line of elements " +
                                "separated by a space.");
                        String path = input.nextLine();
                        File file = new File(path);
                        LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                        v1 = fileInput.readVector();
                    }
                    Matrix A0 = ConvolutionalMatrix.getA0Matrix(v1);
                    Matrix A1 = ConvolutionalMatrix.getA1Matrix(v1);
                    Vector encoded = ConvolutionalMatrix.encode(v1);
                    System.out.println("The A0 matrix is: \n" + A0.toString());
                    System.out.println("The A1 matrix is \n" + A1.toString());
                    System.out.println("The encoded binary stream is: \n" + encoded.toString());
                }


                //Decode using Jacobi
                else if (userInput == 7) {
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
                    Vector v0 = jacobi.splitStream(v);
                    Matrix A0 = ConvolutionalMatrix.getA0Matrix(v0);
                    System.out.println("Decoding - please wait");
                    Vector decoded = jacobi.decode(A0, v0);
                    System.out.println("The decoded binary stream is: \n" + decoded.toString() + "\n");

                }

                //Decode using Gauss-Seidel
                else if (userInput == 8) {
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

                //Solve Ax=b with Jacobi
                else if (userInput == 9) {
                    System.out.println("Press 1 to enter through terminal");
                    System.out.println("Press 2 to enter through a file");
                    Matrix a;
                    Vector b;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        System.out.println("Please enter the matrix A.");
                        System.out.println("Enter empty line to terminate!");
                        a = input.readMatrix();
                        System.out.println("Please enter the  Vector b");
                        System.out.println("Separate vector components by "
                                + "using a space.");
                        b = input.readVector();
                    } else {
                        System.out.println("Enter file path.");
                        System.out.println("File must contain augmented matrix");
                        String path = input.nextLine();
                        File file = new File(path);
                        LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                        AugmentedMatrix augmat = fileInput.readAugmentedMatrixFromFile();
                        a = augmat.getMatrix();
                        b = augmat.getVector();
                    }
                    System.out.println();
                    System.out.println("Please enter the  expected tolerance:");
                    double tol = input.nextDouble();
                    System.out.println(LinearAlgebra.jacobi(a, b, tol));
                }


                //Solve Ax=b with Gauss-Seidel
                else if (userInput == 10) {
                    System.out.println("Press 1 to enter through terminal");
                    System.out.println("Press 2 to enter through a file");
                    Matrix a;
                    Vector b;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        System.out.println("Please enter the matrix A.");
                        System.out.println("Enter empty line to terminate!");
                        a = input.readMatrix();
                        System.out.println("Please enter the  Vector b");
                        System.out.println("Separate vector components by "
                                + "using a space.");
                        b = input.readVector();
                    } else {
                        System.out.println("Enter file path.");
                        System.out.println("File must contain augmented matrix");
                        String path = input.nextLine();
                        File file = new File(path);
                        LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                        AugmentedMatrix augmat = fileInput.readAugmentedMatrixFromFile();
                        a = augmat.getMatrix();
                        b = augmat.getVector();
                    }
                    System.out.println();
                    System.out.println("Please enter the  expected tolerance:");
                    double tol = input.nextDouble();
                    System.out.println(LinearAlgebra.gaussSeidel(a, b, tol));
                }

                //Use power-method for eigenvalue/vector
                else if (userInput == 11) {
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

                //Display population from 2010 to 2050
                else if (userInput == 12) {
                    System.out.println("Press 1 to use Leslie Matrix/Initial Population in PDF.");
                    System.out.println("Press 2 to enter custom values.");
                    Matrix leslie;
                    Vector initialPop;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        leslie = power_method.LESLIEMATRIX;
                        initialPop = power_method.INITIALPOP;
                    } else {
                        System.out.println("Press 1 to enter matrix through terminal");
                        System.out.println("Press 2 to enter matrix through a file");
                        String inputTypeLine1 = input.nextLine();
                        int inputType1 = Integer.parseInt(inputTypeLine1);
                        if (inputType1 == 1) {
                            System.out.println("Please enter the Leslie Matrix.");
                            System.out.println("Enter empty line to terminate!");
                            leslie = input.readMatrix();
                            System.out.println("Please enter the initial population vector.");
                            System.out.println("Separate vector components by "
                                    + "using a space.");
                            initialPop = input.readVector();
                        } else {
                            System.out.println("Enter file path for the matrix.");
                            String path = input.nextLine();
                            File file = new File(path);
                            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                            leslie = fileInput.readMatrixFromFile();
                            System.out.println("Enter file path for the initial guess");
                            String path1 = input.nextLine();
                            File file1 = new File(path);
                            LinearAlgebraScanner fileInput1 = new LinearAlgebraScanner(file);
                            initialPop  = fileInput.readVector();
                        }
                    }
                    power_method.getPopulation(leslie, initialPop);

                }
                //Get largest eigenvalue of leslie matrix
                else if (userInput == 13) {
                    System.out.println("Press 1 to use Leslie Matrix/Initial Population in PDF.");
                    System.out.println("Press 2 to enter custom values.");
                    Matrix leslie;
                    String inputTypeLine = input.nextLine();
                    int inputType = Integer.parseInt(inputTypeLine);
                    if (inputType == 1) {
                        leslie = power_method.LESLIEMATRIX;
                    } else {
                        System.out.println("Press 1 to enter matrix through terminal");
                        System.out.println("Press 2 to enter matrix through a file");
                        String inputTypeLine1 = input.nextLine();
                        int inputType1 = Integer.parseInt(inputTypeLine1);
                        if (inputType1 == 1) {
                            System.out.println("Please enter the Leslie Matrix.");
                            System.out.println("Enter empty line to terminate!");
                            leslie = input.readMatrix();
                        } else {
                            System.out.println("Enter file path.");
                            String path = input.nextLine();
                            File file = new File(path);
                            LinearAlgebraScanner fileInput = new LinearAlgebraScanner(file);
                            leslie = fileInput.readMatrixFromFile();
                        }
                    }
                    int eigIterations =  LinearAlgebra.getEigenvalueIterations(leslie, 0.00000001);
                    double eigenvalue = LinearAlgebra.largestEigenvalue(leslie, eigIterations);
                    System.out.println("The largest eigenvalue of the Leslie Matrix is: \n"
                            + eigenvalue);
                }

                //Get adjusted population
                else if (userInput == 14) {
                    Vector initialPop = power_method.INITIALPOP;
                    double[][] leslie = power_method.LESLIEMATRIX.getArray();
                    leslie[0][2] = leslie[0][2]/2;
                    Matrix adjleslie = new Matrix(leslie);
                    power_method.getPopulation(adjleslie, initialPop);
                    int eigIterations =  LinearAlgebra.getEigenvalueIterations(adjleslie, 0.00000001);
                    double eigenvalue = LinearAlgebra.largestEigenvalue(adjleslie, eigIterations);
                    System.out.println("The largest eigenvalue of the Leslie Matrix is: \n"
                            + eigenvalue);
                }

                else if (userInput == 20) {
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
            } catch (FileNotFoundException e) {
                System.out.println("File is not found.");
            }
        }
    }

}