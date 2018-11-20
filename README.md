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



# Psuedo-code
```Java
// empty = 0
// player = 1 (White)
// AI = 2 (Black)

final int X_MAX = 8;
final int Y_MAX = 3;

int[][] b = new int[8][3];  // Board
int[][] HM = new int[4][3]; // Horrizontal Mills
int[] VM = new int[4];      // Vertical Mills



void play(int[][] board, int turn) {

    // check for game over to record state
    int bCount = 0, wCount = 0;
    for (int x = 0; x < X_MAX; x++) {
        for (int y =  0; y < Y_MAX; y++) {
            if (board[x][y] == 1) wCount++;
            if (board[x][y] == 3) bCount++;
        }
    }
    if ((bCount < 3 || wCount < 3)) {
        // record board and turn
    }

    // determine phase
    if (turn <= 24) {
        // placing phase

    } else {
        // sliding phase

    }
}

bool isMill(int x, int y) {
    // vertical mill?
    // horizontal mill?
} 

void take(int[][] board, int turn) {
    sideToTake = (turn%2 == 0) ? 1 : 2;
    // try all valid takes (take opposite side's piece)
    for (int x = 0; x < X_MAX; x++) {
        for (int y =  0; y < Y_MAX; y++) {
            // check for valid take
            if (board[x][y] == sideToTake && !isMill(x, y)) {
                board[x][y] = 0;
                // continue play
                play(board, turn+1);
            } else {
                // record board and turn (draw)
            }
        }
    }
}

void place(int[][] board, int turn) {
    // try all valid places
    for (int x = 0; x < X_MAX; x++) {
        for (int y =  0; y < Y_MAX; y++) {
            // check for valid place
            // TODO: Copy board??
            if (board[x][y] == 0) {
                board[x][y] = (turn%2 == 0) ? 2 : 1; 
                if (isMill(x, y)) {
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
    // record all valid slides
}
```