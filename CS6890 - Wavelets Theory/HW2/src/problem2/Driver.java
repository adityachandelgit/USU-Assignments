package problem2;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import problem1.OneDHaar;

/**
 * Created by Aditya on 5/28/2016.
 */
public class Driver {

    //File paths
    static final String IMAGE_SOURCE_DIR = "Images/";
    static final String OPENCV_DLL_PATH = "C:/Users/Aditya/Desktop/opencv/build/java/x64/opencv_java2413.dll";

    //Load DLL for Open CV
    static {
        System.load(OPENCV_DLL_PATH);
    }

    //Constants for pixel values
    public final static double WAVELET_COEFF_THRESH = 20.0;
    public final static double EDGE_MARK = 255.0;
    public final static double NO_EDGE_MARK = 0.0;
    public final static double[] NO_EDGE_PIX = {NO_EDGE_MARK, NO_EDGE_MARK, NO_EDGE_MARK};
    public final static double[] YES_EDGE_PIX = {EDGE_MARK, EDGE_MARK, EDGE_MARK};

    public static void main(String[] args) {
        //Option 0 for row FHWT and 1 for column FHWT
        markFHWTEdgeHillTopsInRows("ornament_01.jpg", "ornament_01-edges-row.jpg", 0);
        markFHWTEdgeHillTopsInRows("ornament_01.jpg", "ornament_01-edges-column.jpg", 1);
        markFHWTEdgeHillTopsInRows("ornament_02.jpg", "ornament_02-edges-row.jpg", 0);
        markFHWTEdgeHillTopsInRows("ornament_02.jpg", "ornament_02-edges-column.jpg", 1);
    }

    public static void markFHWTEdgeHillTopsInRows(String infile, String outfile, int option) {
        Mat orig = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }
        final int num_rows = orig.rows();
        final int num_cols = orig.cols();
        Mat grayscale = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        Imgproc.cvtColor(orig, grayscale, Imgproc.COLOR_RGB2GRAY);

        if (option == 0) {
            //Row based FHWT
            double[] fhwt_row_pix = new double[num_rows];
            for (int row = 0; row < num_rows; row++) {
                for (int col = 0; col < num_cols; col++) {
                    fhwt_row_pix[col] = grayscale.get(row, col)[0];
                }
                OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(fhwt_row_pix, 1);
                for (int col = 1; col < num_cols; col += 2) {
                    if (Math.abs(fhwt_row_pix[col]) >= WAVELET_COEFF_THRESH) {
                        if (fhwt_row_pix[col] < 0) {
                            orig.put(row, col, YES_EDGE_PIX);
                            orig.put(row, col - 1, NO_EDGE_PIX);
                        } else if (fhwt_row_pix[col] > 0) {
                            orig.put(row, col, NO_EDGE_PIX);
                            orig.put(row, col - 1, YES_EDGE_PIX);
                        }
                    } else {
                        orig.put(row, col, NO_EDGE_PIX);
                        orig.put(row, col - 1, NO_EDGE_PIX);
                    }
                }
            }
        }


        if (option == 1) {
            //Column based FHWT
            double[] fhwt_col_pix = new double[num_cols];
            for (int col = 0; col < num_cols; col++) {
                for (int row = 0; row < num_rows; row++) {
                    fhwt_col_pix[row] = grayscale.get(row, col)[0];
                }
                OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(fhwt_col_pix, 1);
                for (int row = 1; row < num_rows; row += 2) {
                    if (Math.abs(fhwt_col_pix[row]) >= WAVELET_COEFF_THRESH) {
                        if (fhwt_col_pix[row] < 0) {
                            orig.put(row, col, YES_EDGE_PIX);
                            orig.put(row - 1, col, NO_EDGE_PIX);
                        } else if (fhwt_col_pix[row] > 0) {
                            orig.put(row, col, NO_EDGE_PIX);
                            orig.put(row - 1, col, YES_EDGE_PIX);
                        }
                    } else {
                        orig.put(row, col, NO_EDGE_PIX);
                        orig.put(row - 1, col, NO_EDGE_PIX);
                    }
                }
            }
        }

        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, orig);
        orig.release();
        System.out.println(outfile + " file created in /Images folder");
    }

    //Apply FHWT row by row
    public static void applyRowBasedFHWT(String infile, String outfile) {
        Mat orig = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }
        final int num_rows = orig.rows();
        final int num_cols = orig.cols();
        Mat grayscale = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        Imgproc.cvtColor(orig, grayscale, Imgproc.COLOR_RGB2GRAY);
        double[] fhwt_row_pix = new double[num_rows];
        for (int row = 0; row < num_rows; row++) {
            for (int col = 0; col < num_cols; col++) {
                fhwt_row_pix[col] = grayscale.get(row, col)[0];
            }
            OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(fhwt_row_pix, 1);
            for (int col = 1; col < num_cols; col += 2) {
                if (Math.abs(fhwt_row_pix[col]) >= WAVELET_COEFF_THRESH) {
                    if (fhwt_row_pix[col] < 0) {
                        orig.put(row, col, YES_EDGE_PIX);
                        orig.put(row, col - 1, NO_EDGE_PIX);
                    } else if (fhwt_row_pix[col] > 0) {
                        orig.put(row, col, NO_EDGE_PIX);
                        orig.put(row, col - 1, YES_EDGE_PIX);
                    }
                } else {
                    orig.put(row, col, NO_EDGE_PIX);
                    orig.put(row, col - 1, NO_EDGE_PIX);
                }
            }
        }
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, orig);
        orig.release();
    }

    //Apply FHWT column by column
    public static void applyColumnBasedFHWT(String infile, String outfile) {
        Mat orig = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }
        final int num_rows = orig.rows();
        final int num_cols = orig.cols();
        Mat grayscale = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        Imgproc.cvtColor(orig, grayscale, Imgproc.COLOR_RGB2GRAY);
        double[] fhwt_col_pix = new double[num_cols];

        for (int col = 0; col < num_cols; col++) {
            for (int row = 0; row < num_rows; row++) {
                fhwt_col_pix[row] = grayscale.get(row, col)[0];
            }
            OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(fhwt_col_pix, 1);
            for (int row = 1; row < num_rows; row += 2) {
                if (Math.abs(fhwt_col_pix[row]) >= WAVELET_COEFF_THRESH) {
                    if (fhwt_col_pix[row] < 0) {
                        orig.put(row, col, YES_EDGE_PIX);
                        orig.put(row - 1, col, NO_EDGE_PIX);
                    } else if (fhwt_col_pix[row] > 0) {
                        orig.put(row, col, NO_EDGE_PIX);
                        orig.put(row - 1, col, YES_EDGE_PIX);
                    }
                } else {
                    orig.put(row, col, NO_EDGE_PIX);
                    orig.put(row - 1, col, NO_EDGE_PIX);
                }
            }
        }
        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, orig);
        orig.release();
    }


}
