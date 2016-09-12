package HW4Sudoku;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Aditya on 11/15/2015.
 */
public class Utilities {

    static void exportSolution(String filename, int[][] matrix) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
            bw.write(String.valueOf(matrix[0].length));
            bw.newLine();
            for (int[] aMatrix : matrix) {
                for (int anAMatrix : aMatrix) {
                    bw.write(anAMatrix + " ");
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(new JFrame(), "Problem saving file!");
        }
    }
}
