package Sudoku;
import java.util.*;
class Solver {
    int[][] board;
    ArrayList < Rule > rules;
    public Solver() {
        rules = new ArrayList < Rule > ();
    }
    public boolean sudokuRules(int r, int c) {
        //Check row
        for (int i = 0; i < 9; i++) {
            if (i != c && board[r][i] == board[r][c])
                return false;
        }

        //Check column
        for (int i = 0; i < 9; i++) {
            if (i != r && board[i][c] == board[r][c])
                return false;
        }

        //Check box
        int boxStartR, boxEndR;
        int boxStartC, boxEndC;

        boxStartR = (r / 3) * 3;
        boxEndR = boxStartR + 3;

        boxStartC = (c / 3) * 3;
        boxEndC = boxStartC + 3;

        for (int i = boxStartR; i < boxEndR; i++) {
            for (int j = boxStartC; j < boxEndC; j++) {
                if (i != r && j != c && board[i][j] == board[r][c])
                    return false;
            }
        }
        return true;
    }
    private boolean valid(int r, int c) {
        if (!sudokuRules(r, c))
            return false;

        for (Rule rule: rules) {
            if (!rule.check(board, r, c))
                return false;
        }

        return true;
    }
    public Solver addRule(Rule r) {
        rules.add(r);
        return this;
    }
    private boolean blank(int[] bl) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                if (board[i][j] == 0) {
                    bl[0] = i;
                    bl[1] = j;
                    return true;
                }
        }

        return false;
    }
    public static boolean inside(int r, int c) {
        return !(r < 0 || r > 8 || c < 0 || c > 8);
    }
    public boolean solve(int[][] board) {
        Stack < Attempt > stack = new Stack < Attempt > ();

        this.board = board;

        int[] bl = new int[2];
        if (!blank(bl))
            return true;

        stack.push(new Attempt(bl[0], bl[1]));

        Attempt top;
        while (!stack.empty()) {
            top = stack.peek();

            if (top.i > 9) {
                stack.pop();
                board[top.r][top.c] = 0;
            }

            for (; top.i < 10; top.i++) {
                board[top.r][top.c] = top.i;
                if (valid(top.r, top.c)) {
                    if (!blank(bl))
                        return true;

                    stack.push(new Attempt(bl[0], bl[1]));

                    top.i++;
                    break;
                }
            }
        }
        return false;
    }
    public void print(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
    public static boolean validSudoku(int[][] board) {
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][] box = new int[9][10];

        int current;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                current = board[i][j];
                row[i][current]++;
                col[j][current]++;
                int boxIndex = (i / 3) * 3 + j / 3;
                box[boxIndex][current]++;

                if (row[i][current] > 1 || col[j][current] > 1 || box[boxIndex][current] > 1)
                    return false;
            }
        }
        return true;
    }
}
