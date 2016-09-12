import HW4Sudoku.SudokuBoard;
import HW4Sudoku.RecursiveBruteSudokuSolver;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by Aditya on 11/15/2015.
 */
public class TestSolver {

    int[][] sudoku = {
            {8,6,0,0,2,0,0,0,0},
            {0,0,0,7,0,0,0,5,9},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,6,0,8,0,0},
            {0,4,0,0,0,0,0,0,0},
            {0,0,5,3,0,0,0,0,7},
            {0,0,0,0,0,0,0,0,0},
            {0,2,0,0,0,0,6,0,0},
            {0,0,7,5,0,9,0,0,0}
    };

    private int[] sortedLine = {1,2,3,4,5,6,7,8,9};

    SudokuBoard sb = new SudokuBoard(sudoku);
    RecursiveBruteSudokuSolver ss = new RecursiveBruteSudokuSolver(sb);

    void runSolver() {
        ss.solve(0, 0);
    }

    @Test
    public void testIsSolved() {
        runSolver();
        for (int i = 0; i < 9; i++) {
            assertTrue(arrayPassed(sb.getRow(i)));
            assertTrue(arrayPassed(sb.getColumn(i)));
        }

    }

    private boolean arrayPassed(int[] toVerify) {
        int[] copy = Arrays.copyOf(toVerify, 9);
        Arrays.sort(copy);
        return Arrays.equals(sortedLine, copy);
    }

}
