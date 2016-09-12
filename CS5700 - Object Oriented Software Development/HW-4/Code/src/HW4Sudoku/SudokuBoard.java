package HW4Sudoku;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Created by Aditya on 11/15/2015.
 */
public class SudokuBoard {
    private JLabel[][] cells;
    private JPanel panel = new JPanel();

    final int EMPTY = 0;
    public final int size;
    final int box_size;

    private int[][] board;

    public SudokuBoard(int size) {
        board = new int[size][size];
        this.size = size;
        this.box_size = (int) Math.sqrt(size);
        cells = new JLabel[size][size];
        panel.setLayout(new GridLayout(size, size));
        Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++)  {
                cells[row][col] = new JLabel("", SwingConstants.CENTER);
                cells[row][col].setBorder(border);
                panel.add(cells[row][col]);
            }
        }
    }

    public SudokuBoard(int[][] board) {
        this(board.length);
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                setCell(board[row][col], row, col);
            }
        }
    }

    public void setCell(int num, int row, int col) {
        board[row][col] = num;
        String text = (num == EMPTY) ? "-" : String.valueOf(num);
        cells[row][col].setText(text);
    }
    
    public int getCell(int row, int col) {
        return board[row][col];
    }

    public int[] getRow(int row) {
        int[] theRow = new int[board.length];
        for(int i = 0 ; i < board.length; i++) {
            theRow[i] = board[row][i];
        }
        return theRow;
    }

    public int[] getColumn(int column) {
        int[] theColumn = new int[board.length];
        for(int i = 0; i < board.length; i++)
        {
            theColumn[i] = board[i][column];
        }
        return theColumn;
    }
    
    public JPanel getPanel() {
        return panel;
    }

    public void export() {
        Utilities.exportSolution("Solved/Sudoku_Solved.txt", board);
        JOptionPane.showMessageDialog(new JFrame(), "Solution saved at: Solved/Sudoku_Solved.txt");
    }


}
