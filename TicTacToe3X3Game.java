import java.util.*;
public class TicTacToe3X3Game {
    static Scanner sc = new Scanner(System.in);
    public static void printArray(int[] arr) {
        System.out.println("The array is : " + " ");
        for (int i = 0; i < arr.length; i++) {
            if (arr.length == 1) {
                System.out.println("[ " + arr[i] + " ]");
                break;
            }
            if (i == arr.length - 1)
                System.out.print(arr[i] + " ]");
            else if (i == 0) {
                System.out.print("[ " + arr[i] + ", ");
            } else System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }
    // MINI PROJECT - 2 :  **** 3 X 3 TIC TAC TOE GAME ****
    public static void print2DStringArray(StringBuilder[][] arr) {
        System.out.println("The 2D Array is : ");
        for (StringBuilder[] stringBuilders : arr) {
            for (StringBuilder stringBuilder : stringBuilders) {
                System.out.print("  " + stringBuilder + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void TicTacToe3X3() {
        int n = 3;
        System.out.println("*** RULES FOR THE GAME *** ");
        System.out.println("1 -> Make sure to enter only x or o as input in lower case( case sensitive ).");
        System.out.println("2 -> Don't overwrite any value.");
        System.out.println("3 -> Only " + n * n + " moves are available.");
        System.out.println("4 -> If Player 1 enters x then Player 2 has to enter o for each move and vice versa.");
        System.out.println();
        System.out.println("*** GAME STARTS ***");
        System.out.println();
        // DISPLAY ARRAY CREATION :
        StringBuilder[][] displayArr = new StringBuilder[n][n];
        // INTEGER ARRAY CREATION :
        int [][] calcArray = new int[n][n];
        int r ;
        int c;
        //*** MAIN ARRAY : ROW & COLUMN INDEXING ***
        // DISPLAY ARRAY INDEXING :
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                displayArr[i][j] = new StringBuilder(String.valueOf((10 * i) + j));
                if (i == 0) displayArr[i][j] = new StringBuilder("0" + displayArr[i][j]);
            }
        }
        // INTEGER ARRAY INDEXING :
        for (int i = 0; i < displayArr.length; i++) for (int j = 0; j < displayArr[i].length; j++) calcArray[i][j] = (10 * i) + j;
        print2DStringArray(displayArr);
        //*** OVERWRITE PREVENTION ARRAY CREATION ***
        int[][] cheat = new int[n][n];
        //*** OVERWRITE ARRAY FILLING ***
        for (int[] numb : cheat) {
            Arrays.fill(numb, -1);
        }
        //*** PLAYER TURN RECORDER ***
        int turn = 1;
        int chances = n * n;
        char x = 'p';
        char y = 'p';
        while (chances > 0) {
            System.out.println("Player " + turn + " 's turn.");
            //*** ACTUAL GAME STARTS ***
            System.out.println("Choose row : ");
            r = sc.nextInt();
            if (r >= n || r < 0) {
                System.out.println("Please enter a valid row number.");
                continue;
            }
            System.out.println("Choose column : ");
            c = sc.nextInt();
            if (c >= n || c < 0) {
                System.out.println("Please enter a valid column number.");
                continue;
            }

            //*** PLAYER INPUT AUTHENTICATION ***
            if (chances == n * n) {
                try {
                    System.out.println("Player " + turn + " enter x or o at " + r + " th row and " + c + " th column : ");
                    x = sc.next().charAt(0);
                    cheat[r][c] = 0;
                    displayArr[r][c].setCharAt(0,x);
                    try {
                        displayArr[r][c].deleteCharAt(1);
                    }catch (Exception e){
                        // Do nothing
                    }
                    int w = x;
                    calcArray[r][c] = w;
                    if (x == 'x') y = 'o';
                    else if (x == 'o') y = 'x';
                }
                catch (Exception e){
                    System.out.println("Invalid input, please enter 'x' or 'o'.");
                    continue;
                }
            }
            else {
                //*** OVERWRITE CHECKING ***
                if (turn == 1) {
                    if (cheat[r][c] != -1) {
                        System.out.println("Overwriting not allowed.");
                        System.out.println();
                        continue;
                    }
                    System.out.println("Enter " + x + " at " + r + " th row and " + c + " th column : ");
                    char l = sc.next().charAt(0);
                    if (l!= x){
                        System.out.println("Player " + turn + " can only enter " + x + " for this round.");
                        continue;
                    }
                    cheat[r][c] = 0;
                    displayArr[r][c].setCharAt(0,l);
                    calcArray[r][c] = x;
                    try {
                        displayArr[r][c].deleteCharAt(1);
                    }catch (Exception e){
                        // Do nothing
                    }
                } else if (turn == 2) {
                    //*** OVERWRITE CHECKING ***
                    if (cheat[r][c] != -1) {
                        System.out.println("Overwriting not allowed.");
                        System.out.println();
                        continue;
                    }
                    System.out.println("Enter " + y + " at " + r + " th row and " + c + " th column : ");
                    char m = sc.next().charAt(0);
                    if (m!= y){
                        System.out.println("Player " + turn + " can only enter " + y + " for this round.");
                        continue;
                    }
                    cheat[r][c] = 0;
                    calcArray[r][c] = m;
                    displayArr[r][c].setCharAt(0,m);
                    try {
                        displayArr[r][c].deleteCharAt(1);
                    }catch (Exception e){
                        // Do nothing
                    }
                }
            }
            //*** INPUT ELEMENT VALIDATION ***
            if(x == 'x' || x == 'o') {
                print2DStringArray(displayArr);
                if (checkWinner(calcArray)) {
                    print2DStringArray(displayArr);
                    System.out.println("Player " + turn + " entering '" + displayArr[r][c] + "' wins the round.");
                    return;
                }
                if (turn == 1) turn ++;
                else turn --;
            }
            //*** IF INVALID INPUT IS PROVIDED ***
            else {
                System.out.println("Invalid input, please enter 'x' or 'o'.");
                continue;
            }
            // DEFINES MOVES LEFT
            chances--;
            System.out.println(chances + " moves left.");
            if (chances == 0) {
                System.out.println("Round Draw, no moves left.");
                return;
            }
        }
    }
    public static boolean checkWinner(int [][] calcArray) {
        int n = calcArray.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                //*** WINNING CONDITIONS ***
                if (i + 2 < n && j + 2 < n) {
                    if ((calcArray[i][j] == calcArray[i][j + 1]) && (calcArray[i][j + 1] == calcArray[i][j + 2])) return true;
                    else if ((calcArray[i][j] == calcArray[i + 1][j]) && (calcArray[i + 1][j] == calcArray[i + 2][j])) return true;
                    else if (calcArray[i][j] == calcArray[i + 1][j + 1] && calcArray[i + 1][j + 1] == calcArray[i + 2][j + 2]) return true;
                }
                if (i - 1 >= 0 && i + 1 < n) {
                    if ((calcArray[i][j] == calcArray[i - 1][j]) && (calcArray[i - 1][j] == calcArray[i + 1][j])) return true;
                }
                if (j - 1 >= 0 && j + 1 < n) {
                    if ((calcArray[i][j] == calcArray[i][j - 1]) && (calcArray[i][j - 1] == calcArray[i][j + 1])) return true;
                }
                if (i - 2 >= 0 && j + 2 < n) {
                    if (calcArray[i][j] == calcArray[i - 1][j + 1] && calcArray[i - 1][j + 1] == calcArray[i - 2][j + 2]) return true;
                }
            }
        }
        return false;
    }
    public static void restartTicTacToe3X3() {
        int start = 0;
        int restart = 0;
        int total_rounds = 0;
        System.out.println("Do you want to start the game ?");
        System.out.println("Enter 1, if yes otherwise enter any other digit : ");
        try {
            start = sc.nextInt();
        }
        catch (Exception e){
            System.out.println("Invalid input, please enter 1 or any other digit : ");
        }
        if (start != 1) {
            try {
                System.out.println("**** GAME OVER ****");
                System.out.println("Number of rounds played : " + total_rounds);
                return;
            }
            catch (Exception e) {
                System.out.println("**** GAME OVER ****");
                System.out.println("Number of rounds played : " + total_rounds);
            }
        }
        else {
            TicTacToe3X3();
            System.out.println("**** ROUND OVER ****");
            total_rounds++;
            System.out.println("Number of rounds played : " + total_rounds);
            System.out.println();
        }
        while (true) {
            System.out.println("Do you want to restart the game ?");
            System.out.println("Enter 1, if yes otherwise enter any other digit : ");
            try {
                restart = sc.nextInt();
            }
            catch (Exception e) {
                // Do nothing
            }
            if (restart != 1) {
                try {
                    System.out.println("**** GAME OVER ****");
                    System.out.println("Number of rounds played : " + total_rounds);
                    System.out.println();
                    return;
                }
                catch (Exception e) {
                    System.out.println("**** GAME OVER ****");
                    System.out.println("Number of rounds played : " + total_rounds);
                    System.out.println();
                }
            }
            else {
                TicTacToe3X3();
                System.out.println("**** ROUND OVER ****");
                total_rounds++;
                System.out.println("Number of rounds played : " + total_rounds);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        restartTicTacToe3X3();
    }
}
