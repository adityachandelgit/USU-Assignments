import java.util.ArrayList;

/**
 * Created by Aditya on 6/10/2016.
 */
public class TwoDHaar {

    public static ArrayList<double[][]> orderedForwardDWTForNumIters(double[][] sample, int n, int num_iters) {
        double[][] averages = sample;
        // Array to store final output i.e. 2D Haar transformed signal
        ArrayList<double[][]> finalOutput = new ArrayList<>();
        // Array to store output of one iteration
        ArrayList<double[][]> singleIterationOutput;
        // Loop for  the number of iterations
        for(int numIters = num_iters, size = n; numIters > 0; size--, numIters--) {
            singleIterationOutput = TwoDHaar.orderedForwardDWTForNumItersPerIteration(averages, size);
            averages = singleIterationOutput.get(0);
            // Get horizontal H wavelet
            finalOutput.add(singleIterationOutput.get(1));
            // Get vertical V wavelet
            finalOutput.add(singleIterationOutput.get(2));
            // Get diagonal D wavelet
            finalOutput.add(singleIterationOutput.get(3));
        }
        finalOutput.add(averages);
        return finalOutput;
    }

    public static ArrayList<double[][]> orderedForwardDWTForNumItersPerIteration(double[][] sample, int n) {
        ArrayList<double[][]> output = new ArrayList<>();
        // If the matrix is 2x2, return it.
        if (n <= 0) {
            output.add(sample);
            output.add(sample);
            output.add(sample);
            output.add(sample);
            return output;
        } else {
            final int size = (int) Math.pow(2, n);
            final int halfSize = size / 2;
            // Create 4 matrices (for A, H, V and D), half the size of original matrix
            double[][] average = new double[halfSize][halfSize];
            double[][] horizontal = new double[halfSize][halfSize];
            double[][] vertical = new double[halfSize][halfSize];
            double[][] diagonal = new double[halfSize][halfSize];
            // Matrix for holding intermediate temporary result
            double[][] tempMatrix = new double[2][2];
            for (int i = 0, row = 0; row < size; i++, row += 2) {
                for (int j = 0, col = 0; col < size; j++, col += 2) {
                    // Get A, H, V and D from original matrix and store in in temp matrix.
                    tempMatrix[0][0] = sample[row][col];
                    tempMatrix[0][1] = sample[row][col + 1];
                    tempMatrix[1][0] = sample[row + 1][col];
                    tempMatrix[1][1] = sample[row + 1][col + 1];
                    // Apply FHWT to the temp matrix
                    TwoDHaar.inPlaceFastHaarWaveletTransform(tempMatrix, 2);
                    // Put back the FHW transformed matrix
                    average[i][j] = tempMatrix[0][0];
                    horizontal[i][j] = tempMatrix[0][1];
                    vertical[i][j] = tempMatrix[1][0];
                    diagonal[i][j] = tempMatrix[1][1];
                }
            }
            // Add them
            output.add(average);
            output.add(horizontal);
            output.add(vertical);
            output.add(diagonal);
            return output;
        }
    }

    // Function to do FHWT to a signal starting from (0, 0) index for the matrix
    public static void inPlaceFastHaarWaveletTransform(double[][] sample, int size) {
        inPlaceFastHaarWaveletTransformXY(sample, 0, 0, size);
    }

    public static void inPlaceFastHaarWaveletTransformXY(double[][] sample, int row, int col, int size) {
        // Recursion ending condition i.e. base condition
        if (size <= 1) {
            return;
        }
        // If the size of matrix is 2x2, re-arrange elements
        if (size == 2) {
            double a00 = (sample[row][col] + sample[row][col + 1]) / 2;
            double a01 = (sample[row][col] - sample[row][col + 1]) / 2;
            double a10 = (sample[row + 1][col] + sample[row + 1][col + 1]) / 2;
            double a11 = (sample[row + 1][col] - sample[row + 1][col + 1]) / 2;
            sample[row][col] = (a00 + a10) / 2;
            sample[row][col + 1] = (a01 + a11) / 2;
            sample[row + 1][col] = (a00 - a10) / 2;
            sample[row + 1][col + 1] = (a01 - a11) / 2;
        } else {
            // If the sample size if more than 3 then divide it into 4 equal quadrants.
            int halvedSize = size / 2;
            // Top left starting position of quadrant 1
            int q1x = row;
            int q1y = col;
            // Top left starting position of quadrant 2
            int q2x = row;
            int q2y = col + halvedSize;
            // Top left starting position of quadrant 3
            int q3x = row + halvedSize;
            int q3y = col;
            // Top left starting position of quadrant 4
            int q4x = row + halvedSize;
            int q4y = col + halvedSize;
            // FHWT each quadrant recursively
            inPlaceFastHaarWaveletTransformXY(sample, q1x, q1y, halvedSize);
            inPlaceFastHaarWaveletTransformXY(sample, q2x, q2y, halvedSize);
            inPlaceFastHaarWaveletTransformXY(sample, q3x, q3y, halvedSize);
            inPlaceFastHaarWaveletTransformXY(sample, q4x, q4y, halvedSize);
            // Calculate averages from each of the quadrants
            double[][] averages = new double[2][2];
            averages[0][0] = sample[q1x][q1y];
            averages[0][1] = sample[q1x][q1y + halvedSize];
            averages[1][0] = sample[q1x + halvedSize][q1y];
            averages[1][1] = sample[q1x + size][q1y + size];
            // Transform 2D BHWT to 1D Row-Based HWT
            sample[q1x][q1y] = (averages[0][0] + averages[0][1]) / 2;
            sample[q2x][q2y] = (averages[0][0] - averages[0][1]) / 2;
            sample[q3x][q3y] = (averages[1][0] + averages[1][1]) / 2;
            sample[q4x][q4y] = (averages[1][0] - averages[1][1]) / 2;
            // Do 1D Column-Based HWT
            double a00 = (sample[q1x][q1y] + sample[q3x][q3y]) / 2;
            double h00 = (sample[q2x][q2y] + sample[q4x][q4y]) / 2;
            double v00 = (sample[q1x][q1y] - sample[q3x][q3y]) / 2;
            double d00 = (sample[q2x][q2y] - sample[q4x][q4y]) / 2;
            // Place the result in the matrix
            sample[q1x][q1y] = a00;
            sample[q2x][q2y] = h00;
            sample[q3x][q3y] = v00;
            sample[q4x][q4y] = d00;
        }
    }

    // Apply 2D Inverse Fast Haar Wavelet Transform on the ordered haar transform for a specific number of iterations.
    public static void orderedInverseDWTForNumIters(ArrayList<double[][]> signal, int num_iters) {
        for (int i = 0; i < num_iters; i++) {
            TwoDHaar.orderedInverseDWTForNumIters1x(signal);
        }
    }

    public static void orderedInverseDWTForNumIters1x(ArrayList<double[][]> signal) {
        int size = signal.size();
        //Get average, which is the last element in the signal list
        double[][] averages = signal.get(size - 1);
        //Get average, which is second last element in the signal list
        double[][] diagonals = signal.get(size - 2);
        //Get average, which is third last element in the signal list
        double[][] verticals = signal.get(size - 3);
        //Get average, which is fourth last element in the signal list
        double[][] horizontals = signal.get(size - 4);
        final int len = averages[0].length;
        // Create matrix of double size
        double[][] output = new double[2 * len][2 * len];
        for (int row = 0, i = 0; row < len; row++, i += 2) {
            for (int col = 0, j = 0; col < len; col++, j += 2) {
                double[][] inv = new double[2][2];
                double[][] ordInv = new double[2][2];
                ordInv[0][0] = averages[row][col];
                ordInv[0][1] = horizontals[row][col];
                ordInv[1][0] = verticals[row][col];
                ordInv[1][1] = diagonals[row][col];
                final double avg = ordInv[0][0];
                int[][] avg_matrix = {{1, 1}, {1, 1}};
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        inv[x][y] = avg * avg_matrix[x][y];
                    }
                }
                final double hor = ordInv[0][1];
                int[][] hori_matrix = {{1, -1}, {1, -1}};
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        inv[x][y] += hor * hori_matrix[x][y];
                    }
                }
                final double ver = ordInv[1][0];
                int[][] verti_matrix = {{1, 1}, {-1, -1}};
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        inv[x][y] += ver * verti_matrix[x][y];
                    }
                }
                final double diag = ordInv[1][1];
                int[][] diag_matrix = {{1, -1}, {-1, 1}};
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        inv[x][y] += diag * diag_matrix[x][y];
                    }
                }
                for (int x = 0; x < 2; x++) {
                    System.arraycopy(inv[x], 0, ordInv[x], 0, 2);
                }
                for (int x = 0; x < 2; x++) {
                    System.arraycopy(ordInv[x], 0, output[i + x], j, 2);
                }
            }
        }
        for(int i = 1; i <= 4; i++) {
            signal.remove(size - i);
        }
        signal.add(output);
    }

}
