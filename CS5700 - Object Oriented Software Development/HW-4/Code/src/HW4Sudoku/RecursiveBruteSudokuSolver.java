package HW4Sudoku;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Created by Aditya on 11/15/2015.
 */
public class RecursiveBruteSudokuSolver extends SudokuSolver {

    SudokuBoard sb;

    public RecursiveBruteSudokuSolver(SudokuBoard sudokuBoard) {
        this.sb = sudokuBoard;
        final JPanel panel = sudokuBoard.getPanel();

        Runnable runner = new Runnable() {
            public void run() {
                final JFrame frame = new JFrame("Sudoku Solver");
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

                ActionListener solveBtnListener = new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        if (! solve(0, 0)) {
                            JOptionPane.showMessageDialog(frame, "This puzzle cannot be solved!");
                        } else {
                            sb.export();
                        }
                    }
                };

                frame.setLayout(new GridBagLayout());
                addToSwing(frame, panel, 0, 0, 1, 1);
                
                JButton btnSolve = new JButton("Solve!");
                btnSolve.addActionListener(solveBtnListener);
                addToSwing(frame, btnSolve, 0, 1, 1, 1);

                frame.setSize(500, 600);
                frame.setVisible(true);
            }
        };
        EventQueue.invokeLater(runner);
    }

    @Override
    public boolean solve(int row, int col) {
        int nextCol = (col + 1) % sb.size;
        int nextRow = (nextCol == 0) ? row + 1 : row;

        try {
            if (sb.getCell(row, col) != sb.EMPTY)
                return solve(nextRow, nextCol);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }

        for (int i = 1; i <= sb.size; i++) {
            if (check(i, row, col)) {
                sb.setCell(i, row, col);
                if (solve(nextRow, nextCol)) {
                    return true;
                }
            }
        }
        sb.setCell(sb.EMPTY, row, col);

        return false;
    }

    private boolean check(int num, int row, int col) {
        int r = (row / sb.box_size) * sb.box_size;
        int c = (col / sb.box_size) * sb.box_size;

        for (int i = 0; i < sb.size; i++) {
            if (sb.getCell(row, i) == num ||
                    sb.getCell(i, col) == num ||
                    sb.getCell(r + (i % sb.box_size), c + (i / sb.box_size)) == num) {
                return false;
            }
        }
        return true;
    }

    private static void addToSwing(Container container, Component component,
                                   int gridx, int gridy, int gridwidth, int gridheight) {
        Insets insets = new Insets(30, 10, 10, 10);
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth,
                gridheight, 1, 1, GridBagConstraints.CENTER,
                GridBagConstraints.BOTH, insets, 0, 0);
        container.add(component, gbc);
    }


}
