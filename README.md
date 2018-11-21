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