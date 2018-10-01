import java.util.Arrays;
import java.util.Random;

public class SudokuGenerator {
    private char board[][] = new char[9][9];
    private int[] randomizeSudoku = new int[9];
    private char transposedSeed[][] = new char[][]{{'1', '2', '3', '4', '5', '6', '7', '8', '9'},
            {'4', '5', '6', '7', '8', '9', '1', '2', '3'},
            {'7', '8', '9', '1', '2', '3', '4', '5', '6'},
            {'2', '3', '1', '5', '6', '4', '8', '9', '7'},
            {'5', '6', '4', '8', '9', '7', '2', '3', '1'},
            {'8', '9', '7', '2', '3', '1', '5', '6', '4'},
            {'3', '1', '2', '6', '4', '5', '9', '7', '8'},
            {'6', '4', '5', '9', '7', '8', '3', '1', '2'},
            {'9', '7', '8', '3', '1', '2', '6', '4', '5'},};
    private char seed[][] = new char[9][9];
    private Random random = new Random();

    public static void main(String[] args) {
        SudokuGenerator s = new SudokuGenerator();
        int n = 10, i = 1;
        long start = System.currentTimeMillis();
        while (i <= n) {
            System.out.println("------Number of Board : " + (i) + "  ----------\n");
            s.transpose();
            s.shuffle();
            s.display();
            s.seedChanger();
            i++;
        }
        long end = System.currentTimeMillis();
        System.out.println(" Time consumed : " + (end - start) + " mil sec");
    }

    private void transpose() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                seed[j][i] = transposedSeed[i][j];
            }
        }
    }

    private void seedChanger() {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(board[i], 0, transposedSeed[i], 0, board.length);
        }
    }

    private void randomSudokuGenerator() {
        for (int i = 0; i < randomizeSudoku.length; i++) {
            randomizeSudoku[i] = 9;
        }
        int i = 0;
        for (; i < randomizeSudoku.length; ++i) {
            int r = random.nextInt(2);
            for (int i1 = 0; i1 < i; ++i1) {
                int x = randomizeSudoku[i1];
                if (x == r) {
                    if (i < 3) {
                        r = random.nextInt(3);
                    } else if (i < 6) {
                        r = random.nextInt(3) + 3;
                    } else if (i < 9) {
                        r = random.nextInt(3) + 6;
                    }
                    i1 = -1;
                }
            }
            randomizeSudoku[i] = r;
        }
    }

    private void shuffle() {
        randomSudokuGenerator();
        System.out.println(Arrays.toString(randomizeSudoku));
        for (int x = 0; x < 9; x++) {
            board[0][x] = seed[randomizeSudoku[0]][x];
            board[1][x] = seed[randomizeSudoku[1]][x];
            board[2][x] = seed[randomizeSudoku[2]][x];
            board[3][x] = seed[randomizeSudoku[3]][x];
            board[4][x] = seed[randomizeSudoku[4]][x];
            board[5][x] = seed[randomizeSudoku[5]][x];
            board[6][x] = seed[randomizeSudoku[6]][x];
            board[7][x] = seed[randomizeSudoku[7]][x];
            board[8][x] = seed[randomizeSudoku[8]][x];
        }
        for (int x = 0; x < 9; x++) {

            if (randomizeSudoku[0] == 0) swapping(board, x, 1, 0);
            if (randomizeSudoku[0] == 1) swapping(board, x, 2, 0);
            if (randomizeSudoku[0] == 0) swapping(board, x, 5, 4);
            if (randomizeSudoku[0] == 1) swapping(board, x, 5, 3);
            if (randomizeSudoku[0] == 2) swapping(board, x, 8, 6);
        }
    }

    private void swapping(char a[][], int commonIndex, int first, int second) {
        char swap = a[commonIndex][first];
        a[commonIndex][first] = a[commonIndex][second];
        board[commonIndex][second] = swap;
    }

    private void display() {
        int i, j;
        for (i = 0; i <= 8; ++i) {
            if (i == 0) {
                System.out.print("\t\t\t_______________________________________\n\t row " + (i + 1) + "\t");
            } else {
                System.out.print("\t\t\t|---|---|---||---|---|---||---|---|---|\n\t row " + (i + 1) + "\t");
            }
            for (j = 0; j <= 8; ++j) {
                if (j == 3) {
                    System.out.print("|");
                }
                if (j == 6) {
                    System.out.print("|");
                }
                if (j == 8) {
                    System.out.println("| " + board[i][j] + " |");
                } else {
                    System.out.print("| " + board[i][j] + " ");
                }
            }
            if (i == 2) {
                System.out.println("\t\t\t|---|---|---||---|---|---||---|---|---|");
            }
            if (i == 5) {
                System.out.println("\t\t\t|---|---|---||---|---|---||---|---|---|");
            }
            if (i == 8) {
                System.out.println("\t\t\t---------------------------------------");
                System.out.println("\tcolumns   1   2   3    4   5   6    7   8   9  \n\n\n");
            }
        }
    }
}
