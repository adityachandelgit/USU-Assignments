import HW4Sudoku.SudokuBoardParser;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Aditya on 11/15/2015.
 */
public class TestParser {

    int[][] sudoku = {
            {0,0,2,3},
            {0,0,0,0},
            {0,0,0,0},
            {3,4,0,0}
    };

    @Test
    public void testParsingLogic() {
        int[][] puzzle = SudokuBoardParser.parse(0);
        sort2D(sudoku);
        sort2D(puzzle);
        Assert.assertArrayEquals(sudoku, puzzle);
    }


    public static void sort2D(int[][] array) {
        for (int[] arr : array) {
            Arrays.sort(arr);
        }

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return new BigInteger(Arrays.toString(o1).replaceAll("[\\[\\], ]", ""))
                        .compareTo(new BigInteger(Arrays.toString(o2).replaceAll("[\\[\\], ]", "")));
            }
        });
    }


}
