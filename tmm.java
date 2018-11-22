import java.util.Arrays;

public class tmm {
    // empty = 0
    // player = 1 (White)
    // AI = 2 (Black)
    // game begins with empty board on turn 1

    // board = int[X_MAX][Y_MAX]
    private static int MAX_ROWS = 3;
    private static int MAX_COLS = 8;

    public static void main(String[] args) {
        int[][] emptyBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},   // row 0
            {1, 2, 0, 0, 0, 0, 0, 0},   // row 1
            {1, 0, 0, 0, 0, 0, 0, 0}    // row 2
        };

        int[][] varient = {
            {0, 0, 0, 1, 0, 0, 0, 0},   // row 0
            {0, 0, 2, 1, 0, 0, 0, 0},   // row 1
            {0, 0, 0, 0, 0, 0, 0, 0}    // row 2
        };

        System.out.println(isSymmetry(emptyBoard, varient));
        
        //play(emptyBoard, 1);
    }

    public static void play(int[][] board, int turn) {
        System.out.println();
        // determine phase
        if (turn <= 2) {
            // placing phase
            place(board, turn);

        } else if (turn <= 2) {
            // sliding phase
            slide(board, turn);

            // check for game over
            int bCount = 0;
            int wCount = 0;
            for (int r = 0; r < MAX_ROWS; r++) {
                for (int c =  0; c < MAX_COLS; c++) {
                    if (board[r][c] == 1) wCount++;
                    if (board[r][c] == 2) bCount++;
                }
            }
            if ((bCount <= 2 || wCount <= 2)) {
                // record board and turn
                System.out.println("***** Game Over! *****");
                //printBoard(board);
                //System.out.println("**********************");
                return;
            }
        }
    }

    public static void printBoard(int[][] board) {
        for (int r =  0; r < MAX_ROWS; r++) {
            for (int c = 0; c < MAX_COLS; c++) {
                System.out.print(board[r][c] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static Boolean isSymmetry(int[][] board, int[][] branch)  {
        // check for equality
        if (Arrays.deepEquals(board, branch)) return true;

        // rotate branch
        int [][] rotate = new int[MAX_ROWS][MAX_COLS];
        for (int i=1; i<=3; i++) {
            for (int r=0; r<MAX_ROWS; r++) {
                for (int c=0; c<MAX_COLS; c++) {
                    rotate[r][c] = branch[r][(c+(2*i))%MAX_COLS];
                }
            }
            // check rotation for equality
            System.out.println("rotation");
            printBoard(rotate);
            if (Arrays.deepEquals(board, rotate)) return true;
        }

        // reflect branch
        int [][] reflect = new int[MAX_ROWS][MAX_COLS];
        int leftCol,rightCol, oppositeCol;
        for (int pivotCol=0; pivotCol<MAX_COLS; pivotCol++) {
            for (int r=0; r<MAX_ROWS; r++) {
                oppositeCol = (pivotCol + 4)%MAX_COLS;

                reflect[r][pivotCol] = branch[r][pivotCol];
                reflect[r][oppositeCol] = branch[r][oppositeCol];
                for (int c=1; c<=3; c++) {
                    leftCol = (MAX_COLS - c + pivotCol)%MAX_COLS;
                    rightCol = (MAX_COLS + c + pivotCol)%MAX_COLS;

                    reflect[r][leftCol] = branch[r][rightCol];
                    reflect[r][rightCol] = branch[r][leftCol];
                }
            }
            System.out.println("reflection");
            printBoard(reflect);

            // rotate the reflection
            for (int i=1; i<=3; i++) {
                for (int r=0; r<MAX_ROWS; r++) {
                    for (int c=0; c<MAX_COLS; c++) {
                        rotate[r][c] = reflect[r][(c+(2*i))%MAX_COLS];
                    }
                }
                // check reflection rotation for equality
                System.out.println("reflection rotation");
                printBoard(rotate);
                if (Arrays.deepEquals(board, rotate)) return true;
            }
        }

        // check inside out symmetry
        int[][] insideOut = new int[MAX_ROWS][MAX_COLS];
        insideOut[0] = branch[2].clone();
        insideOut[1] = branch[1].clone();
        insideOut[2] = branch[0].clone();

        // check for equality
        System.out.println("insideOut");
        printBoard(insideOut);
        if (Arrays.deepEquals(board, insideOut)) return true;

        // rotate insideOut
        for (int i=1; i<=3; i++) {
            for (int r=0; r<MAX_ROWS; r++) {
                for (int c=0; c<MAX_COLS; c++) {
                    rotate[r][c] = insideOut[r][(c+(2*i))%MAX_COLS];
                }
            }
            // check rotation for equality
            System.out.println("insideOut rotation");
            printBoard(rotate);
            if (Arrays.deepEquals(board, rotate)) return true;
        }

        // reflect insideOut
        for (int pivotCol=0; pivotCol<MAX_COLS; pivotCol++) {
            for (int r=0; r<MAX_ROWS; r++) {
                oppositeCol = (pivotCol + 4)%MAX_COLS;

                reflect[r][pivotCol] = insideOut[r][pivotCol];
                reflect[r][oppositeCol] = insideOut[r][oppositeCol];
                for (int c=1; c<=3; c++) {
                    leftCol = (MAX_COLS - c + pivotCol)%MAX_COLS;
                    rightCol = (MAX_COLS + c + pivotCol)%MAX_COLS;

                    reflect[r][leftCol] = insideOut[r][rightCol];
                    reflect[r][rightCol] = insideOut[r][leftCol];
                }
            }
            System.out.println("insideOut reflection");
            printBoard(reflect);

            // rotate the reflection
            for (int i=1; i<=3; i++) {
                for (int r=0; r<MAX_ROWS; r++) {
                    for (int c=0; c<MAX_COLS; c++) {
                        rotate[r][c] = reflect[r][(c+(2*i))%MAX_COLS];
                    }
                }
                // check reflection rotation for equality
                System.out.println("insideOut reflection rotation");
                printBoard(rotate);
                if (Arrays.deepEquals(board, rotate)) return true;
            }
        }

        return false;
    }

    public static Boolean isMill(int[][] board, int row, int col) {
        //System.out.println("isMill() (" + x + ", " + y + ")");
        System.out.println("isMill()");

        Boolean isMill = true;

        //  vertical mill?
        int side = board[0][col];
        for (int r=1; r<MAX_ROWS; r++) {
            if (board[r][col] != side) isMill = false;
        }
        if (isMill) return true;

        // horizontal mill?
        // check position of target within mill (left, right, or center)
        return false;
    } 

    public static void take(int[][] board, int turn) {
        System.out.println("take()");

        int sideToTake = (turn%2 == 0) ? 1 : 2;
        Boolean mustTakeMill = true;
        // try all valid takes (no mills)
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c =  0; c < MAX_COLS; c++) {
                // check for valid take
                if (board[r][c] == sideToTake && !isMill(board, r, c)) {
                    mustTakeMill = false;
                    board[r][c] = 0;
                    // continue play
                    play(board, turn+1);
                } 
            }
        }

        // try all valid takes (with mills)
        if (mustTakeMill) {
            for (int r = 0; r < MAX_ROWS; r++) {
                for (int c =  0; c < MAX_COLS; c++) {
                    // check for valid take
                    if (board[r][c] == sideToTake) {
                        board[r][c] = 0;
                        // continue play
                        play(board, turn+1);
                    } 
                }
            }
        }
    }

    public static void place(int[][] board, int turn) {
        System.out.println("place(turn " + turn + ")");

        // try all valid places
        int[][] branch = new int[MAX_ROWS][MAX_COLS];
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c =  0; c < MAX_COLS; c++) {
                // check for valid place
                if (board[r][c] == 0) {
                    for (int i=0; i<MAX_ROWS;i++) {
                        branch[i] = board[i].clone();
                    }
                    branch[r][c] = (turn%2 == 0) ? 2 : 1; 
                    if (turn >= 6 && isMill(branch, r, c)) {
                        // must take
                        take(branch, turn);
                    } else {
                        // continue play
                        printBoard(branch);
                        play(branch, turn+1);
                    }
                }
            }
        }
    }

    public static void slide(int[][] board, int turn) {
        System.out.println("slide(turn " + turn + ")");

        int sideToMove = (turn%2 == 0) ? 2 : 1;

        // try all valid slides
        int[][] branch = new int[MAX_ROWS][MAX_COLS];
        Boolean canSlide = false;
        for (int r = 0; r < MAX_ROWS; r++) {
            for (int c =  0; c < MAX_COLS; c++) {
                if (board[r][c] == sideToMove) {
                    int leftCol = ((MAX_COLS-1) + c)%MAX_COLS;
                    int rightCol = ((MAX_COLS+1) + c)%MAX_COLS;
                    //System.out.println("(" + leftCol + ", " + c + ", " + rightCol + ")");
                    // check left
                    if (board[r][leftCol] == 0) {
                        canSlide = true;
                        for (int i=0; i<MAX_ROWS;i++) {
                            branch[i] = board[i].clone();
                        }
                        branch[r][c] = 0;
                        branch[r][leftCol] = sideToMove;
                        if (turn >= 6 && isMill(branch, r, leftCol)) {
                            // must take
                            take(branch, turn);
                        } else {
                            printBoard(branch);
                            play(branch, turn+1);
                        }
                    }
                    // check right
                    if (board[r][rightCol] == 0) {
                        canSlide = true;
                        for (int i=0; i<MAX_ROWS;i++) {
                            branch[i] = board[i].clone();
                        }
                        branch[r][c] = 0;
                        branch[r][rightCol] = sideToMove;
                        if (turn >= 6 && isMill(branch, r, rightCol)) {
                            // must take
                            take(branch, turn);
                        } else {
                            printBoard(branch);
                            play(branch, turn+1);
                        }
                    }
    
                    if (r==0) { // top row
                        // check down
                        if (board[r+1][c] == 0) {
                            canSlide = true;
                        }
                    } else if (r==1) { // middle row
                        // check up
                        if (board[r-1][c] == 0) {
                            canSlide = true;
                        }
                        // check down
                        if (board[r+1][c] == 0) {
                            canSlide = true;
                        }
                    } else if (r==2) { // bottom row
                        // check up
                        if (board[r-1][c] == 0) {
                            canSlide = true;
                        }
                    }
                }
            }
        }
        // check for no slides available
        if (!canSlide) {
            // record game board and turn (draw)
            System.out.println("Can't Slide");
        }
    }
}