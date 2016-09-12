import java.util.ArrayList;

/**
 * @author Vladimir Kulyukin
 */
public class WaveletAlgos_Summer2016_HW4 {

    static double[][] signal_2x2_1 = {
            {9, 7},
            {5, 3}
    };

    static double[][] signal_2x2_2 = {
            {1, 0},
            {0, 1}
    };

    static double[][] signal_2x2_3 = {
            {255, 100},
            {50, 250}
    };

    static double[][] signal_4x4_1 = {
            {9, 7, 6, 2},
            {5, 3, 4, 4},
            {8, 2, 4, 0},
            {6, 0, 2, 2}
    };

    static double[][] signal_4x4_2 = {
            {6.0, 8.0, 10.0, 8.0},
            {10.0, 4.0, 8.0, 2.0},
            {2.0, 6.0, 0.0, 6.0},
            {10.0, 6.0, 6.0, 10.0}
    };

    static double[][] signal_8x8_1 = {
            {255, 0, 0, 0, 0, 0, 0, 100},
            {0, 255, 0, 0, 0, 0, 100, 0},
            {0, 0, 255, 0, 0, 100, 0, 0},
            {0, 0, 0, 250, 100, 0, 0, 0},
            {0, 0, 0, 120, 150, 0, 0, 0},
            {0, 0, 120, 0, 0, 150, 0, 0},
            {0, 120, 0, 0, 0, 0, 150, 0},
            {120, 0, 0, 0, 0, 0, 0, 150}
    };

    static double[][] signal_8x8_2 = {
            {255, 0, 0, 0, 0, 0, 0, 0},
            {0, 255, 0, 0, 0, 0, 0, 0},
            {0, 0, 255, 0, 0, 0, 0, 0},
            {0, 0, 0, 255, 0, 0, 0, 0},
            {0, 0, 0, 0, 255, 0, 0, 0},
            {0, 0, 0, 0, 0, 255, 0, 0},
            {0, 0, 0, 0, 0, 0, 255, 0},
            {0, 0, 0, 0, 0, 0, 0, 255}
    };

    public static void test_ordered_haar(double[][] data, int n, int num_iters) {
        System.out.println("Input signal");
        displaySignal(data);
        ArrayList<double[][]> transform = TwoDHaar.orderedForwardDWTForNumIters(data, n, num_iters);
        displayTransformedSignal(transform, num_iters);

        TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);

        System.out.println("Inverted signal");
        displayInvertedSignal(transform);
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
    }

    private static void displaySignal(double[][] data) {
        for (double[] aData : data) {
            for (double anAData : aData) {
                System.out.print(anAData + " ");
            }
            System.out.println();
        }
    }

    public static void displayTransformedSignal(ArrayList<double[][]> transform, int num_iters) {
        System.out.println("Transformed signal after " + num_iters  + " iterations");

        // The general pattern here is that:
        // 1. The last element in the arrayList containing the transformed signal contains the average.
        // 2. Starting from 1st element of arraylist, every 3rd element contains horizontal wavelet H, (1, 4, 7..)
        // 3. Starting from 2nd element of arraylist, every 4th element contains vertical wavelet V, (2, 5, 8..)
        // 3. Starting from 3rd element of arraylist, every 5th element contains vertical wavelet V, (3, 6, 9..)
        for (int index = 0; index < transform.size(); index++) {
            double[][] data = transform.get(index);
            if(num_iters == 1) {
                // Element at index 0 of the arrayList is the horizontal wavelet H
                if(index  == 0) {
                    System.out.println("horizontal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 1 of the arrayList is the vertical wavelet V
                if(index  == 1) {
                    System.out.println("vertical coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 2 of the arrayList is the diagonal wavelet D
                if(index  == 2) {
                    System.out.println("diagonal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 4 of the arrayList is the average wavelet A
                if(index == 3) {
                    System.out.println("mean coeffs for " + data.length + " x " + data.length);
                }
            }
            if(num_iters == 2) {
                // Element at index 0 and 3 of the arrayList is the horizontal wavelet H
                if(index  == 0 || index  == 3) {
                    System.out.println("horizontal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 1 and 4 of the arrayList is the vertical wavelet V
                if(index  == 1 || index  == 4) {
                    System.out.println("vertical coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 2 and 5 of the arrayList is the diagonal wavelet D
                if(index  == 2 || index  == 5) {
                    System.out.println("diagonal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 6 of the arrayList is the average wavelet A
                if(index == 6) {
                    System.out.println("mean coeffs for " + data.length + " x " + data.length);
                }
            }
            if(num_iters == 3) {
                // Element at index 0, 3 and 6 of the arrayList is the horizontal wavelet H
                if(index  == 0 || index  == 3 || index  == 6) {
                    System.out.println("horizontal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 1, 4 and 7 of the arrayList is the vertical wavelet H
                if(index  == 1 || index  == 4 || index  == 7) {
                    System.out.println("vertical coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 2, 5 and 8 of the arrayList is the diagonal wavelet H
                if(index  == 2 || index  == 5 || index  == 8) {
                    System.out.println("diagonal coeffs for " + data.length + " x " + data.length);
                }
                // Element at index 9 of the arrayList is the horizontal wavelet A
                if(index == 9) {
                    System.out.println("mean coeffs for " + data.length + " x " + data.length);
                }
            }
            for (double[] column : data) {
                for (double element : column) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
    }

    private static void displayInvertedSignal(ArrayList<double[][]> transform) {
        for(double[][] data : transform) {
            for (double[] aData : data) {
                for (double anAData : aData) {
                    System.out.print(anAData + " ");
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        test_ordered_haar(signal_2x2_1, 1, 1);
        test_ordered_haar(signal_2x2_2, 1, 1);
        for (int num_iters = 1; num_iters <= 2; num_iters++) {
            test_ordered_haar(signal_4x4_2, 2, num_iters);
        }
        for (int num_iters = 1; num_iters <= 3; num_iters++) {
            test_ordered_haar(signal_8x8_1, 3, num_iters);
        }
        for (int num_iters = 1; num_iters <= 3; num_iters++) {
            test_ordered_haar(signal_8x8_1, 3, num_iters);
        }
    }
}