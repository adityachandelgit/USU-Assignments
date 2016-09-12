import HW4Sudoku.SudokuBoard;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBoard {

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

    SudokuBoard sb = new SudokuBoard(sudoku);

    @Test
    public void testRows() {
        int[] row0 = {8,6,0,0,2,0,0,0,0};
        assertTrue(Arrays.equals(row0, sb.getRow(0)));
    }

    @Test
    public void testColumns() {
        int[] column0 = {8,0,0,0,0,0,0,0,0};
        assertTrue(Arrays.equals(column0, sb.getColumn(0)));
    }

    @Test
    public void testGetCell() {
        assertEquals(8, sb.getCell(0, 0));
    }

    @Test
    public void testSetCell() {
        sb.setCell(3, 0, 5);
        assertEquals(3, sb.getCell(0, 5));
    }
}