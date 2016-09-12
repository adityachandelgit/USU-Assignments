package problem1;

/**
 * Created by Aditya on 6/4/2016.
 */
public class Main {

    public static void main(String[] args) {
        test_fwd_inv_cdf24_for_num_iters(sig1, 1);
        test_fwd_inv_cdf24_for_num_iters(sig2, 1);
        test_fwd_inv_cdf24_for_num_iters(sig3, 1);
        test_fwd_inv_cdf24_for_num_iters(sig3, 2);
        test_fwd_inv_cdf24_for_num_iters(sig6, 1);
        test_fwd_inv_cdf24_for_num_iters(sig6, 2);
        test_fwd_inv_cdf24_for_num_iters(sig6, 3);
    }

    static double[] sig1 = {1, 2, 3, 4};
    static double[] sig2 = {4, 3, 2, 1};
    static double[] sig3 = {1, 2, 3, 4, 5, 6, 7, 8};
    static double[] sig4 = {8, 7, 6, 5, 4, 3, 2, 1};
    static double[] sig5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    static double[] sig6 = {16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};


    static void display_signal(double[] signal) {
        for (double d : signal) {
            System.out.print(d + " ");
        }
    }

    static void test_fwd_inv_cdf24_for_num_iters(double[] s, int num_iters) {
        System.out.print("Input: ");
        display_signal(s);
        System.out.println();
        double[] scopy = new double[s.length];
        System.arraycopy(s, 0, scopy, 0, scopy.length);
        CDF24.orderedForwardDWTForNumIters(s, num_iters);
        System.out.print("FWD CDF(2,4) for num iters " + num_iters + ": ");
        display_signal(s);
        System.out.println();
        CDF24.orderedInverseDWTForNumIters(s, num_iters);
        System.out.print("INV CDF(2,4) for num iters " + num_iters + ": ");
        display_signal(s);
        System.out.println();
        System.out.println();
    }

}
