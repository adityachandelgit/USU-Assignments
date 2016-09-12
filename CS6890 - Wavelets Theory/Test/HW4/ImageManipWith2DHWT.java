package org.vkedco.wavelets.summer2016.hw4;

import java.util.ArrayList;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import org.vkedco.wavelets.haar.OneDHaar;
import org.vkedco.wavelets.haar.TwoDHaar;

/**
 * @author Vladimir Kulyukin
 */
public class ImageManipWith2DHWT {

    // change these constants to reflect your configurations.
    static final String IMAGE_SOURCE_DIR =
            "C:/Users/vladimir/research/images/";
    static final String OPENCV_DLL_PATH =
            "C:/Users/Vladimir/programming/opencv/build/java/x64/opencv_java249.dll";

    // Use a static code block to load the dll/so;
    static {
        System.load(OPENCV_DLL_PATH);
    }

    
    
    public static void downSampleImageWithZeroWavelets(String infile, String outfile, int num_iters) {
	// Your code            
}
    
    public static void downSampleImageWithZeroDiagWavelets(String infile, String outfile, int num_iters) {
        //Your code
    }
    
    public static void downSampleImageWithZeroHorWavelets(String infile, String outfile, int num_iters) {
        //your code
    }
    
    public static void downSampleImageWithZeroVerWavelets(String infile, String outfile, int num_iters) {
        // your code
    }

    public static void main(String[] args) {
        downSampleImageWithZeroWavelets("ornament_01.jpg", "ornament_01_dwn_all_zero.jpg", 3);
        downSampleImageWithZeroDiagWavelets("ornament_01.jpg", "ornament_01_dwn_dig_zero.jpg", 3);
        downSampleImageWithZeroHorWavelets("ornament_01.jpg", "ornament_01_dwn_hor_zero.jpg", 3);
        downSampleImageWithZeroVerWavelets("ornament_01.jpg", "ornament_01_dwn_ver_zero.jpg", 3);
        
     
    }
}
