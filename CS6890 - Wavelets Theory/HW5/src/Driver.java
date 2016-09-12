import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Aditya on 5/28/2016.
 */
public class Driver {

    //File paths
    static final String IMAGE_SOURCE_DIR = "Pics/";
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
        processImageDirectory(IMAGE_SOURCE_DIR);
    }

    public static void processImageDirectory(String dir) {
        File folder = new File(dir);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    if (!file.getName().substring(Math.max(file.getName().length() - 10, 0)).equals("LINES.png")) {
                        detectAreasAndDrawLines(file.getName(), file.getName().substring(0, file.getName().lastIndexOf('.')) + "-LINES.png");
                    }
                }
            }
        }
    }

    public static void detectAreasAndDrawLines(String infile, String outfile) {
        Mat orig = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }
        final int num_rows = orig.rows();
        final int num_cols = orig.cols();
        Mat grayscale = new Mat(num_rows, num_cols, CvType.CV_8UC1);
        Imgproc.cvtColor(orig, grayscale, Imgproc.COLOR_RGB2GRAY);

        //Column based FHWT
        double[] fhwt_col_pix = new double[num_cols];
        for (int col = 0; col < num_cols; col++) {
            for (int row = 0; row < num_rows; row++) {
                fhwt_col_pix[row] = grayscale.get(row, col)[0];
            }
            OneDHaar.inPlaceFastHaarWaveletTransformForNumIters(fhwt_col_pix, 5);
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

        ArrayList<Integer> possible_rows = new ArrayList<>();

        for (int row = 0; row < orig.rows(); row++) {
            int lineLength = 0;
            int maxLineLength = 0;
            int brokenLines = 0;
            for (int column = 0; column < orig.cols(); column++) {
                double pixelValue = orig.get(row, column)[0];
                if (pixelValue == EDGE_MARK) {
                    lineLength++;
                } else {
                    if (maxLineLength < lineLength) {
                        maxLineLength = lineLength;
                        if (lineLength > 20) {
                            brokenLines++;
                        }
                    }
                    lineLength = 0;
                }
            }
            if ((lineLength > 60 || maxLineLength > 60) || (brokenLines > 1 && maxLineLength > 20)) {
                possible_rows.add(row);
            }
        }

        int lineGroup = 0;
        for (int i = 0; i < possible_rows.size(); i++) {
            if (possible_rows.size() > (i + 1)) {
                int current = possible_rows.get(i);
                int next = possible_rows.get(i + 1);
                if (next - current > 10) {
                    lineGroup++;
                }
                if (lineGroup > 1) {
                    possible_rows.subList(i + 1, possible_rows.size()).clear();
                }
            }
        }

        /*for (Integer possible_row : possible_rows) {
            System.out.println(possible_row.intValue());
        }*/

        Mat original = Highgui.imread(IMAGE_SOURCE_DIR + infile);
        if (orig.rows() == 0 || orig.cols() == 0) {
            throw new IllegalArgumentException("Failed to read " + IMAGE_SOURCE_DIR + infile);
        }

        final int num_rows_original = original.rows();
        final int num_cols_original = original.cols();
        Mat colorImage = new Mat(num_rows_original, num_cols_original, CvType.CV_32S);
        Imgproc.cvtColor(original, colorImage, CvType.CV_32S);
        //colorImage.convertTo(original, CvType.CV_8UC3, 255.0);

        double[] yellow = {0, 255, 255};
        for (int i = 0; i < possible_rows.size(); i++) {
            for (int j = 1; j < colorImage.cols(); j++) {
                original.put(possible_rows.get(i), j, yellow);
            }
        }

        Highgui.imwrite(IMAGE_SOURCE_DIR + outfile, original);
        orig.release();
        original.release();
        System.out.println(outfile + " file created in /Images folder");
    }

}
