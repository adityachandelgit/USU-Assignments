package HW4Sudoku;

import javax.swing.*;

/**
 * Created by Aditya on 11/15/2015.
 */
public class Main {

    public static void main(String[] args) {
        int size = selectPuzzleSize();
        int[][] board = SudokuBoardParser.parse(size);
        new RecursiveBruteSudokuSolver(new SudokuBoard(board));
    }

    private static int selectPuzzleSize() {
        String[] options = new String[] {"4 x 4", "9 x 9", "16 x 16", "25 x 25", "4 x 4 Unsolvable", "9 x 9 Unsolvable"};
        int response = JOptionPane.showOptionDialog(null, "Select Puzzle Size", "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        System.out.println(response);
        return response;
    }


}
