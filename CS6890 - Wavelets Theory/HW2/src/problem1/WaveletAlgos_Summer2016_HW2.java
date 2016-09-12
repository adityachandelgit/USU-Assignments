package problem1;

/**
 *
 * @author Vladimir Kulyukin
 */
public class WaveletAlgos_Summer2016_HW2 {

    public static void main(String[] args) {
        lecture_3_ordered_fhwt_example_1();
        lecture_3_ordered_fhwt_example_2(1);
        lecture_3_ordered_fhwt_example_2(2);
        lecture_3_ordered_fhwt_example_3(1);
        lecture_3_ordered_fhwt_example_3(2);
        lecture_3_ordered_fhwt_example_3(3);

        lecture_3_inplace_fhwt_example_1(1);
        lecture_3_inplace_fhwt_example_1(2);
        lecture_3_inplace_fhwt_example_2(1);
        lecture_3_inplace_fhwt_example_2(2);
        lecture_3_inplace_fhwt_example_2(3);
    }
    
    static void displaySignal(double[] sig) {
        for(double sample: sig) {
            System.out.print(sample + "\t");
        }
        System.out.println();
    }
    
    public static void lecture_3_ordered_fhwt_example_1() {
        System.out.println("Wavelet Agorithms: Lecture 3: Ordered FHWT Example 1");
        final double[] signal = {11, 3};
        System.out.print("Signal: "); displaySignal(signal);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signal, 1);
        System.out.print("Fordward Ordered HWT: "); displaySignal(signal);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signal, 1);
        System.out.print("Inverse Ordered  HWT: "); displaySignal(signal);
        System.out.println();
    }
    
    public static void lecture_3_ordered_fhwt_example_2(int num_iters) {
        System.out.println("Wavelet Agorithms: Lecture 3: Ordered FHWT Example 2");
        final double[] signal = {5, 1, 2, 8};
        System.out.print("Signal: "); displaySignal(signal);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Fordward Ordered HWT; num scales = " + num_iters + ": "); displaySignal(signal);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Inverse Ordered HWT; num scales  = " + num_iters + ": "); displaySignal(signal);
        System.out.println();
    }
    
    public static void lecture_3_ordered_fhwt_example_3(int num_iters) {
        System.out.println("Wavelet Agorithms: Lecture 3: Ordered FHWT Example 3");
        final double[] signal = {3, 1, 0, 4, 8, 6, 9, 9};
        System.out.print("Signal: "); displaySignal(signal);
        OneDHaar.orderedFastHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Fordward Ordered HWT; num scales = " + num_iters + ": "); displaySignal(signal);
        OneDHaar.orderedFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Inverse Ordered HWT; num scales  = " + num_iters + ": "); displaySignal(signal);
        System.out.println();
    }
    
    public static void lecture_3_inplace_fhwt_example_1(int num_iters) {
        System.out.println("Wavelet Agorithms: Lecture 3: In-Place FHWT Example 1");
        final double[] signal = {5, 1, 2, 8};
        System.out.print("Signal: "); displaySignal(signal);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Fordward Ordered HWT; num scales = " + num_iters + ": "); displaySignal(signal);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Inverse Ordered HWT; num scales  = " + num_iters + ": "); displaySignal(signal);
        System.out.println();
    }
    
    public static void lecture_3_inplace_fhwt_example_2(int num_iters) {
        System.out.println("Wavelet Agorithms: Lecture 3: In-Place FHWT Example 2");
        final double[] signal = {3, 1, 0, 4, 8, 6, 9, 9};
        System.out.print("Signal: "); displaySignal(signal);
        OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Fordward Ordered HWT; num scales = " + num_iters + ": "); displaySignal(signal);
        OneDHaar.inPlaceFastInverseHaarWaveletTransformForNumIters(signal, num_iters);
        System.out.print("Inverse Ordered HWT; num scales  = " + num_iters + ": "); displaySignal(signal);
        System.out.println();
    }
    

}
