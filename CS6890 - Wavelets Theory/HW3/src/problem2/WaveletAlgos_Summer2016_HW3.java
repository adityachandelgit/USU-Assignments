package problem2;

import problem1.CDF24;
import problem1.OneDHaar;

/**
 *
 * @author Vladimir Kulyukin
 */
public class WaveletAlgos_Summer2016_HW3 {
    
    
    static void display_signal(double[] signal) {
        for(double d: signal) {
            System.out.println(d);
        }
    }
    
    static double[] generateSignal() {
        WaveletAlgos_Summer2016_HW3_Curve f = new WaveletAlgos_Summer2016_HW3_Curve();
        double[] domain = Partition.partition(0, 511, 1);
        double[] range = new double[domain.length];
        for(int i = 0; i < domain.length; i++)  {
            range[i] = f.v(domain[i]);
        }
        range[100] = 3;
        range[200] = -3;
        range[300] = 3;
        range[400] = -3;
        domain = null;
        return range;
    }
    
    static void doMultiresAnalysisWithHWT(double[] signal, int num_iters, int range_start, int range_end) {

        // 1. Wavelet coefficients from DWT of the original signal using haar transformation.
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signal, num_iters);
        //display_signal(signal);

        // 2. D8 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 256, 511);

        // 3. D7 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 128, 255);

        // 4. D6 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 64, 127);

        // 5. S6 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 0, 63);

        // Do multi resolution
        for(int i = 0; i < signal.length; i++) {
            if (i < range_start || i > range_end) {
                signal[i] = 0;
            }
        }
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        display_signal(signal);

    }

    static void doMultiresAnalysisWithCDF24(double[] signal, int num_iters, int range_start, int range_end) {
        // 1. Wavelet coefficients from DWT of the original signal using CDF24 transformation.
        CDF24.orderedForwardDWTForNumIters(signal, num_iters);
        //display_signal(signal);

        // 2. D8 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 256, 511);

        // 3. D7 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 128, 255);

        // 4. D6 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 64, 127);

        // 5. S6 coefficient of from the DWT of the original signal.
        //print_partial_signal(signal, 0, 63);

        // Do multi resolution
        for(int i = 0; i < signal.length; i++) {
            if (i < range_start || i > range_end) {
                signal[i] = 0;
            }
        }
        CDF24.orderedInverseDWTForNumIters(signal, num_iters);
        display_signal(signal);
    }
    
    public static void main(String[] args) {
        final double[] signal = generateSignal();
        //System.out.println("Original signal");
        //display_signal(signal);

        //doMultiresAnalysisWithHWT(signal, 1, 0, 255);
        //doMultiresAnalysisWithHWT(signal, 2, 0, 127);
        //doMultiresAnalysisWithHWT(signal, 3, 0, 63);
        //doMultiresAnalysisWithHWT(signal, 4, 0, 31);
        
        //doMultiresAnalysisWithCDF24(signal, 1, 0, 255);
        //doMultiresAnalysisWithCDF24(signal, 2, 0, 127);
        //doMultiresAnalysisWithCDF24(signal, 3, 0, 63);
        doMultiresAnalysisWithCDF24(signal, 4, 0, 31);
    }


    static void print_partial_signal(double[] signal, int start, int end) {
        for(int i = start; i <= end; i++) System.out.println(signal[i]);
    }
    
}
