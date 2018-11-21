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

#### Valid Positions (x, y):
8x3 cylindrical array

#### Valid Mills (M):
vertical and horizontal sets of three.

#### Rules
AI goes second
iterate through all valid moves using a binary search tree
determine wining branch based on current board positions

# TODO
how to handle loops?
how to store all paths (not just endings)?
time estimates?
memory requirements?
turn and variation number tracking?
verify isMill() and isSymmetry() functionality

is there a way minimize for loop recursion?