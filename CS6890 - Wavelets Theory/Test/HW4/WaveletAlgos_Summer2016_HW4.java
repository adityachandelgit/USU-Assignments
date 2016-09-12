package org.vkedco.wavelets.summer2016.hw4;

import java.util.ArrayList;
import org.vkedco.wavelets.haar.TwoDHaar;

/**
 *
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
        {9,	7,	6,	2},
        {5,	3,	4,	4},
        {8,	2,	4,	0},
        {6,	0,	2,	2}
    };
    
    static double[][] signal_4x4_2 = {
        {6.0,	8.0,	10.0,	8.0},
        {10.0,	4.0,	8.0,	2.0},
        {2.0,	6.0,	0.0,	6.0},
        {10.0,	6.0,	6.0,	10.0}
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
    
    public static void displayTransformedSignal(double[][] transform, int num_iters) {
// your code
            }
    
    public static void test_ordered_haar(double[][] data, int n, int num_iters) {
        System.out.println("Input signal");
        displaySignal(data);
        double[][] transform = TwoDHaar.orderedForwardDWTForNumIters(data, n, num_iters);
        displayTransformedSignal(transform, num_iters);

        TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);
  
        System.out.print("Inverted signal ");
        displayInvertedSignal(transform);
        System.out.println("<><><><><><><><><><><><><><><><><><><><><><>");
    }
    
    public static void main(String[] args) {
        test_ordered_haar(signal_2x2_1, 1, 1);
        test_ordered_haar(signal_2x2_2, 1, 1);
        for(int num_iters = 1; num_iters <= 2; num_iters++) {
            test_ordered_haar(signal_4x4_2, 2, num_iters);
        }
        for(int num_iters = 1; num_iters <= 3; num_iters++) {   
            test_ordered_haar(signal_8x8_1, 3, num_iters);
        }
        for(int num_iters = 1; num_iters <= 3; num_iters++) {   
            test_ordered_haar(signal_8x8_1, 3, num_iters);
        }
    }
}
