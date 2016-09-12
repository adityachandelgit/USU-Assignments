package problem1;

/**
 * Created by Aditya on 6/4/2016.
 */
public class CDF24 {

    // H Coefficients for forward 4D HWT
    static double h0 = (1 + Math.sqrt(3)) / (4 * Math.sqrt(2));
    static double h1 = (3 + Math.sqrt(3)) / (4 * Math.sqrt(2));
    static double h2 = (3 - Math.sqrt(3)) / (4 * Math.sqrt(2));
    static double h3 = (1 - Math.sqrt(3)) / (4 * Math.sqrt(2));

    // G Coefficients for forward 4D HWT
    static double g0 = h3;
    static double g1 = -h2;
    static double g2 = h1;
    static double g3 = -h0;

    // H Coefficients for inverse 4D HWT
    static double ih0 = h2;
    static double ih1 = g2;
    static double ih2 = h0;
    static double ih3 = g0;

    // G Coefficients for inverse 4D HWT
    static double ig0 = h3;
    static double ig1 = g3;
    static double ig2 = h1;
    static double ig3 = g1;

    public static void orderedInverseDWTForNumIters(double[] signal_transform, int num_iters) {
        // Sample length
        int len = signal_transform.length;
        // Check if num_iters is power of 2; and sample length >= 4
        if (!(len > 0) && ((len & (len - 1)) == 0) || len < 4) {
            return;
        }
        // Calculate length of transformed signal
        int len_transform = (int) (len / Math.pow(2, (num_iters - 1)));
        for (int i = 0; len_transform <= len; i++, len_transform *= 2) {
            if (i >= num_iters) return;
            int center = len_transform / 2;
            double[] signal_inverse = new double[len_transform];
            signal_inverse[0] = ih0 * signal_transform[center - 1] + ih1 * signal_transform[len_transform - 1] + ih2 * signal_transform[0] + ih3 * signal_transform[center];
            signal_inverse[1] = ig0 * signal_transform[center - 1] + ig1 * signal_transform[len_transform - 1] + ig2 * signal_transform[0] + ig3 * signal_transform[center];
            for (int j = 0, k = 2; j < center - 1; j++, k += 2) {
                signal_inverse[k] = ih0 * signal_transform[j] + ih1 * signal_transform[center + j] + ih2 * signal_transform[j + 1] + ih3 * signal_transform[center + j + 1];
                signal_inverse[k + 1] = ig0 * signal_transform[j] + ig1 * signal_transform[center + j] + ig2 * signal_transform[j + 1] + ig3 * signal_transform[center + j + 1];
            }
            System.arraycopy(signal_inverse, 0, signal_transform, 0, signal_inverse.length);
        }

    }

    public static void orderedForwardDWTForNumIters(double[] signal, int num_iters) {
        // Sample length
        int len = signal.length;
        // Check if num_iters is power of 2; and sample length >= 4
        if (!(len > 0) && ((len & (len - 1)) == 0) || len < 4) {
            return;
        }

        int currScale = 0;
        int inter_len = len;
        int i;
        int j;
        int center;
        // Loop till 4 elements are left in the signal
        while (inter_len >= 4) {
            // Temporary array for storing intermediate values
            double[] temp = new double[inter_len];
            // Half the length
            center = inter_len / 2;
            // Do sweeps
            for (i = 0, j = 0; j < inter_len - 3; i += 1, j += 2) {
                temp[i] = h0 * signal[j] + h1 * signal[j + 1] + h2 * signal[j + 2] + h3 * signal[j + 3];
                temp[center + i] = g0 * signal[j] + g1 * signal[j + 1] + g2 * signal[j + 2] + g3 * signal[j + 3];
            }
            temp[i] = h0 * signal[inter_len - 2] + h1 * signal[inter_len - 1] + h2 * signal[0] + h3 * signal[1];
            temp[center + i] = g0 * signal[inter_len - 2] + g1 * signal[inter_len - 1] + g2 * signal[0] + g3 * signal[1];
            System.arraycopy(temp, 0, signal, 0, temp.length);
            currScale += 1;
            inter_len /= 2;
            if (currScale >= num_iters) {
                return;
            }
        }
    }


}
