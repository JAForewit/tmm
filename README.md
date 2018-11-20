## Twelve Men's Moriss
Brute force AI
```
* ------------ * ------------ *
|  \           |           /  |
|    * ------- * ------- *    |
|    |  \      |      /  |    |
|    |    * -- * -- *    |    |
|    |    |         |    |    |
* -- * -- *         * -- * -- *
|    |    |         |    |    |
|    |    * -- * -- *    |    |
|    |  /      |      \  |    |
|    * ------- * ------- *    |
|  /           |           \  |
* ------------ * ------------ *
```

### Valid Positions (x, y):
8x3 cylindrical array

### Valid Mills (M):

Horizontal: 4x3 index array     (1M:2x, 1M:1y)

Vertical: 0-7 index             (1M: 1x)

AI goes second
iterate through all valid moves using a binary search tree
determine wining branch based on current board positions

how to handle loops?
how to handle pointers and pass-by-reference?
how to store all paths (not just endings)?


# Main
```Java
// empty = 0
// player = 1 (White)
// AI = 2 (Black)
// game begins with empty board on turn 1

// board = int[X_MAX][Y_MAX]
final byte X_MAX = 8;
final byte Y_MAX = 3;

public class Main {

    public static void main(String[] args) {
        int[][] emptyBoard = {
            {0, 0, 0, 0, 0, 0, 0, 0},   // row 0
            {0, 0, 0, 0, 0, 0, 0, 0},   // row 1
            {0, 0, 0, 0, 0, 0, 0, 0}    // row 2
        };
    }

    void play(byte[][] board, byte turn) {
        // check for game over to record state
        byte bCount = 0, wCount = 0;
        for (byte x = 0; x < X_MAX; x++) {
            for (byte y =  0; y < Y_MAX; y++) {
                if (board[x][y] == 1) wCount++;
                if (board[x][y] == 3) bCount++;
            }
        }
        if ((bCount <= 2 || wCount <= 2)) {
            // record board and turn

            return;
        }

        // determine phase
        if (turn <= 24) {
            // placing phase

        } else {
            // sliding phase

        }
    }

    void printBoard(int[][] board) {
        for (int y =  0; y < Y_MAX; y++) {
            for (int x = 0; x < X_MAX; x++) {
                system.out.print(board[x][y] + "  ");
            }
            system.out.print("\n");
        }
    }

    bool isMill(int[][] board, int x, int y) {
        // vertical mill?
        // horizontal mill?
    } 

    void take(int[][] board, int turn) {
        sideToTake = (turn%2 == 0) ? 1 : 2;
        mustTakeMill = true;
        // try all valid takes (no mills)
        for (int x = 0; x < X_MAX; x++) {
            for (int y =  0; y < Y_MAX; y++) {
                // check for valid take
                if (board[x][y] == sideToTake && !isMill(board, x, y)) {
                    mustTakeMill = false;
                    board[x][y] = 0;
                    // continue play
                    play(board, turn+1);
                } 
            }
        }

        // try all valid takes (with mills)
        if (mustTakeMill) {
            for (int x = 0; x < X_MAX; x++) {
                for (int y =  0; y < Y_MAX; y++) {
                    // check for valid take
                    if (board[x][y] == sideToTake) {
                        board[x][y] = 0;
                        // continue play
                        play(board, turn+1);
                    } 
                }
            }
        }

    }

    void place(int[][] board, int turn) {
        // try all valid places
        for (int x = 0; x < X_MAX; x++) {
            for (int y =  0; y < Y_MAX; y++) {
                // check for valid place
                if (board[x][y] == 0) {
                    board[x][y] = (turn%2 == 0) ? 2 : 1; 
                    if (isMill(board, x, y)) {
                        // must take
                        take(board, turn)
                    } else {
                        // continue play
                        play(board, turn+1);
                    }
                }
            }
        }
    }

    void slide(int[][] board, int turn) {
        sideToMove = (turn%2 == 0) ? 2 : 1;
        // try all valid slides
        bool canSlide = false;
        for (int x = 0; x < X_MAX; x++) {
            for (int y =  0; y < Y_MAX; y++) {
                int l = (x-1)%8;
                int r = (x+1)%8;
                // check left
                if (board[l][y] == 0) {
                    canSlide = true;
                    board[x][y] = 0;
                    board[l][y] = sideToMove;
                    if (isMill(board, l, y)) {
                        // must take
                        take(board, turn);
                    } else {
                        play(board, turn+1);
                    }
                }
                // check right
                if (board[r][y] == 0) {
                    canSlide = true;
                    board[x][y] = 0;
                    board[r][y] = sideToMove;
                    if (isMill(board, r, y)) {
                        // must take
                        take(board, turn);
                    } else {
                        play(board, turn+1);
                    }
                }

                if (y==0) { // top row
                    // check down
                    if (board[x][y+1] == 0) {
                        canSlide = true;
                    }
                } else if (y==1) { // middle row
                    // check up
                    if (board[x][y-1] == 0) {
                        canSlide = true;
                    }
                    // check down
                    if (board[x][y+1] == 0) {
                        canSlide = true;
                    }
                } else (y==2) { // bottom row
                    // check up
                    if (board[x][y-1] == 0) {
                        canSlide = true;
                    }
                }
            }
        }
        // check for no slides available
        if (!canSlide) {
            // record game board and turn (draw)
        }
    }
}

```