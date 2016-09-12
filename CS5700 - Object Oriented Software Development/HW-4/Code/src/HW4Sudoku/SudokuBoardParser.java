package HW4Sudoku;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Created by Aditya on 11/6/2015.
 */
public class SudokuBoardParser {

    public static int[][] parse(int option) {

        String[] whichPuzzle = new String[6];
        whichPuzzle[0] = "Puzzles/4x4.txt";
        whichPuzzle[1] = "Puzzles/9x9.txt";
        whichPuzzle[2] = "Puzzles/16x16.txt";
        whichPuzzle[3] = "Puzzles/25x25.txt";
        whichPuzzle[4] = "Puzzles/4x4Unsolvable.txt";
        whichPuzzle[5] = "Puzzles/9x9Unsolvable.txt";

        String selectedPuzzle = null;

        if(option == 0) {
            selectedPuzzle = whichPuzzle[0];
        } else if(option == 1) {
            selectedPuzzle = whichPuzzle[1];
        } else if(option == 2) {
            selectedPuzzle = whichPuzzle[2];
        } else if(option == 3) {
            selectedPuzzle = whichPuzzle[3];
        } else if(option == 4) {
            selectedPuzzle = whichPuzzle[4];
        } else if(option == 5) {
            selectedPuzzle = whichPuzzle[5];
        }


        try {
            assert selectedPuzzle != null;
            List<String> lines = Files.readLines(new File(selectedPuzzle), Charsets.UTF_8);
            int size = lines.size() - 1;
            int[][] board = new int[size][size];
            for(int i = 1; i <= size; i++) {
                StringTokenizer st = new StringTokenizer(lines.get(i));
                int numbTokens = st.countTokens();
                for(int j = 0; j < numbTokens; j++) {
                    String currentToken = st.nextToken();
                    board[i - 1][j] = Integer.parseInt(currentToken);
                }
            }

            for(int i = 0; i < board[0].length; i++) {
                for(int j = 0; j < board.length; j++) {
                    System.out.print(board[i][j] + "  ");
                }
                System.out.println();
            }

            return board;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
