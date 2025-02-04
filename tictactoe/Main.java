package tictactoe;

import java.util.Scanner;

public class Main {
    static boolean finish = false;
    static final char[][] grid = new char[3][3];
    static Scanner scanner = new Scanner(System.in);
    static char player = 'X';

    public static void main(String[] args) {

        initializeGrid(grid);
        showGrid(grid);

        // let the game begins
        while (!finish) {
            addMove(player);
            if (analyze(grid, 'X')) {
                System.out.println("X wins");
                finish = true; break;
            } else if (analyze(grid, 'O')) {
                System.out.println("O wins");
                finish = true; break;
            } else if (isBordFull(grid)) {
                System.out.println("Draw");
                finish = true; break;
            }
        }

        scanner.close();
        System.exit(0);
    }

    private static void initializeGrid(char[][] grid) {
        String s = "_________";
        int index = 0;
        //Followings are for input example for the game initialization with a string like __XOO_XO_
       /* while (true) {
            s = scanner.nextLine();
            if (s.length() == 9 && s.matches("[XO_]+")) {
                break; // Valid input, break the loop
            } else {
                System.out.println("Invalid input. Please make sure the string is exactly 9 characters long and contains only 'X', 'O', or '_'.");
            }
        }*/
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (s.charAt(index) != '_')
                    grid[i][j] = s.charAt(index);
                index++;
            }
        }
    }

    private static void addMove(char c) {
        int row = 0;
        int colomn = 0;
        while (true) {
            String move = scanner.nextLine();
            if (!move.matches("[0-9 ]*")) {
                System.out.println("You should enter numbers!");
                continue;
            }
            try {
                row = Integer.parseInt(String.valueOf(move.charAt(0)));
                colomn = Integer.parseInt(String.valueOf(move.charAt(move.length() - 1)));
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
            }
            if (row > 3 || colomn > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            row = row == 0 ? 0 : row - 1;
            colomn = colomn == 0 ? 0 : colomn - 1;
            if (grid[row][colomn] == '\u0000') {
                grid[row][colomn] = c;
                break;
            } else System.out.println("This cell is occupied! Choose another one!");
        }
        player = player=='X'?'O':'X';
        showGrid(grid);
    }

    private static void showGrid(char[][] grid) {
        System.out.println("---------");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid[i].length; j++) {
                if (j < grid[i].length) {
                    System.out.printf("%c ", grid[i][j] != '\u0000' ? grid[i][j] : ' ');
                }
            }
            System.out.print("|\n");
        }
        System.out.println("---------");
    }

    private static boolean analyze(char[][] grid, char c) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == c && grid[i][1] == c && grid[i][2] == c) {
                return true; // analyze rows
            }
            if (grid[0][i] == c && grid[1][i] == c && grid[2][i] == c) {
                return true;   // analyze columns
            }
        }

        // analyze diagonals
        if (grid[0][0] == grid[1][1] && grid[1][1] == grid[2][2] && grid[0][0] == c) {
            return true;
        }
        if (grid[0][2] == grid[1][1] && grid[1][1] == grid[2][0] && grid[0][2] == c) {
            return true;
        }
        return false;
    }

    private static boolean isBordFull(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                if (c == '\u0000') return false;
            }
        }
        return true;
    }
}
