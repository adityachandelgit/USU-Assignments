
/**
 * Created by Aditya on 7/16/2016.
 */
public class Main {

    public static void main(String[] args) {
        String sequence1 = "PLASMA";
        String sequence2 = "ALTRUISM";

        int minEdits = minimumEditDistance(sequence1.toCharArray(), sequence2.toCharArray());
    }

    static int minimumEditDistance(char[] sequence1, char[] sequence2) {

        int[][] matrix = new int[sequence1.length + 1][sequence2.length + 1];

        for (int i = 0; i <= sequence1.length; i++) {
            for (int j = 0; j <= sequence2.length; j++) {
                if (i == 0) {
                    matrix[i][j] = j;
                } else if (j == 0) {
                    matrix[i][j] = i;
                } else if (sequence1[i - 1] == sequence2[j - 1]) {
                    // If both elements characters of the string are equal, then set the matrix
                    // cell to diagonal value
                    matrix[i][j] = matrix[i - 1][j - 1];
                } else {
                    // If both elements characters of the string are different, then set the matrix
                    // cell to minimum of top, left and diagonal element plus 1
                    matrix[i][j] = 1 + Math.min((Math.min(matrix[i][j - 1], matrix[i - 1][j])), matrix[i - 1][j - 1]);
                }
            }
        }

        // Print the Dynamic Programming matrix
        System.out.print("    ");
        for (int i = 0; i < sequence2.length; i++) {
            System.out.print(sequence2[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            }
            if (i < matrix.length && i != 0) {
                System.out.print(sequence1[i - 1] + " ");
            }
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.println();
        System.out.println("Minimum Edits: " + matrix[sequence1.length - 1][sequence2.length - 1]);
        System.out.println();
        backTrack(matrix, sequence1, sequence2);
        System.out.println();
        System.out.println("-------------------------");

        return matrix[sequence1.length - 1][sequence2.length - 1];
    }

    static void backTrack(int[][] matrix, char[] sequence1, char[] sequence2) {
        int i = matrix.length - 1;
        int j = matrix[0].length - 1;
        while (true) {
            if (i == 0 || j == 0) {
                break;
            }
            if (sequence1[i - 1] == sequence2[j - 1]) {
                i--;
                j--;
            } else if (matrix[i][j] == matrix[i - 1][j - 1] + 1) {
                // Character is replaced if the current element in the DP matrix came from the cell diagonal to it
                System.out.println("Replace " + sequence2[j - 1] + " in Sequence 2 to " + sequence1[i - 1] + " in Sequence 1 ");
                i--;
                j--;
            } else if (matrix[i][j] == matrix[i - 1][j] + 1) {
                // Character is deleted from Sequence 1 if the current element in the DP matrix came from the cell left of it
                System.out.println("Delete " + sequence1[i - 1] + " from Sequence 1");
                i = i - 1;
            } else if (matrix[i][j] == matrix[i][j - 1] + 1) {
                // Character is deleted from Sequence 2 if the current element in the DP matrix came from the cell top of it
                System.out.println("Delete " + sequence2[j - 1] + " from Sequence 2");
                j = j - 1;
            }
        }
    }


}
