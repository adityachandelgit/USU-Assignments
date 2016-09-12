import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

/**
 * @author Vladimir Kulyukin
 */
public class ImageManipWith2DHWT {

    // change these constants to reflect your configurations.
    static final String IMAGE_SOURCE_DIR = "Pics/";
    static final String OPENCV_DLL_PATH = "C:/Users/Aditya/Desktop/opencv/build/java/x64/opencv_java2413.dll";

    // Use a static code block to load the dll/so;
    static {
        System.load(OPENCV_DLL_PATH);
    }

    public static void main(String[] args) {
        /*downSampleImageWithZeroWavelets("ornament_01.jpg", "ornament_01_dwn_all_zero-aditya.jpg", 3);
        downSampleImageWithZeroDiagWavelets("ornament_01.jpg", "ornament_01_dwn_dig_zero-aditya.jpg", 3);
        downSampleImageWithZeroHorWavelets("ornament_01.jpg", "ornament_01_dwn_hor_zero-aditya.jpg", 3);
        downSampleImageWithZeroVerWavelets("ornament_01.jpg", "ornament_01_dwn_ver_zero-aditya.jpg", 3);*/

        //downSampleImageWithZeroWavelets("2015-05-13_12-17-33.png", "2015-05-13_12-17-33-aditya-z.jpg", 3);
        //downSampleImageWithZeroDiagWavelets("2015-05-13_12-17-33.png", "2015-05-13_12-17-33-aditya-d.jpg", 3);
        //downSampleImageWithZeroHorWavelets("2015-05-13_12-17-33.png", "2015-05-13_12-17-33-aditya-h.jpg", 3);
        //downSampleImageWithZeroVerWavelets("2015-05-13_12-17-33.png", "2015-05-13_12-17-33-aditya-v.jpg", 3);
        downSampleImageWithZeroDiagWavelets("2015-05-13_12-17-33.png", "2015-05-13_12-17-33-aditya-v.jpg", 6);
    }

    public static void downSampleImageWithZeroWavelets(String infile, String outfile, int num_iters) {
        double[][] pix_mat = importImageAndConvertToGrayscale(infile);
        int power0f2 = (int) (Math.log(pix_mat.length) / Math.log(2.0));
        ArrayList<double[][]> transform = TwoDHaar.orderedForwardDWTForNumIters(pix_mat, power0f2, num_iters);

        // Set all wavlets to zero.
        if (transform != null) {
            for (int i = 0; i < transform.size() - 1; i++) {
                double[][] d = transform.get(i);
                d = new double[d.length][d.length];
                transform.set(i, d);
            }
        }

        //TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);
        Mat grayscale = convertMatrixToGrayscale(transform);
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, grayscale);
    }


    public static void downSampleImageWithZeroDiagWavelets(String infile, String outfile, int num_iters) {
        double[][] pix_mat = importImageAndConvertToGrayscale(infile);
        int power0f2 = (int) (Math.log(pix_mat.length) / Math.log(2.0));
        ArrayList<double[][]> transform = TwoDHaar.orderedForwardDWTForNumIters(pix_mat, power0f2, num_iters);

        // Set all diagonal wavelets to zero.
        if (transform != null) {
            for (int i = 0; i < transform.size(); i++) {
                if (i == 2 || i == 5 || i == 8) {
                    double[][] d = transform.get(i);
                    d = new double[d.length][d.length];
                    transform.set(i, d);
                }
            }
        }
        TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);
        Mat grayscale = convertMatrixToGrayscale(transform);
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, grayscale);
    }

    // Set all horizontal wavelets to zero.
    public static void downSampleImageWithZeroHorWavelets(String infile, String outfile, int num_iters) {
        double[][] pix_mat = importImageAndConvertToGrayscale(infile);
        int power0f2 = (int) (Math.log(pix_mat.length) / Math.log(2.0));
        ArrayList<double[][]> transform = TwoDHaar.orderedForwardDWTForNumIters(pix_mat, power0f2, num_iters);

        if (transform != null) {
            for (int i = 0; i < transform.size() - 1; i++) {
                if (i == 0 || i == 3 || i == 6) {
                    double[][] d = transform.get(i);
                    d = new double[d.length][d.length];
                    transform.set(i, d);
                }
            }
        }

        TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);
        Mat grayscale = convertMatrixToGrayscale(transform);
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, grayscale);
    }

    // Set all vertical wavelets to zero.
    public static void downSampleImageWithZeroVerWavelets(String infile, String outfile, int num_iters) {
        double[][] pix_mat = importImageAndConvertToGrayscale(infile);
        int power0f2 = (int) (Math.log(pix_mat.length) / Math.log(2.0));
        ArrayList<double[][]> transform = TwoDHaar.orderedForwardDWTForNumIters(pix_mat, power0f2, num_iters);

        if (transform != null) {
            for (int i = 0; i < transform.size() - 1; i++) {
                if (i == 1 || i == 4 || i == 7) {
                    double[][] d = transform.get(i);
                    d = new double[d.length][d.length];
                    transform.set(i, d);
                }
            }
        }

        TwoDHaar.orderedInverseDWTForNumIters(transform, num_iters);
        Mat grayscale = convertMatrixToGrayscale(transform);
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, grayscale);
    }


    private static double[][] importImageAndConvertToGrayscale(String infile) {
        Mat orig = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }
        final int num_rows = orig.rows();
        final int num_cols = orig.cols();
        Mat grayscale = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        Imgproc.cvtColor(orig, grayscale, Imgproc.COLOR_RGB2GRAY);

        double[][] pix_mat = new double[num_rows][num_cols];
        double[] temp;
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                temp = grayscale.get(row, col);
                pix_mat[row][col] = temp[0];
            }
        }
        orig.release();
        return pix_mat;
    }

    private static Mat convertMatrixToGrayscale(ArrayList<double[][]> transform) {
        double[][] pix_mat_inversed = transform.get(0);
        Mat grayscale = new Mat(pix_mat_inversed.length, pix_mat_inversed[0].length, CvType.CV_8UC1);
        for (int row = 0; row < pix_mat_inversed.length; row++) {
            for (int col = 0; col < pix_mat_inversed[0].length; col++) {
                grayscale.put(row, col, pix_mat_inversed[row][col]);
            }
        }
        return grayscale;
    }
}
