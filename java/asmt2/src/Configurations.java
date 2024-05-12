/* implements methods in computerPlay */

public class Configurations {
    public char[][] board;
    int board_size;
    int lengthToWin;
    int max_levels;

    /* constructor */
    public Configurations(int board_size, int lengthToWin, int max_levels) {
        int n = board_size;
        int winL = lengthToWin;
        int lvl = max_levels;

        // initialize empty board of size n x n
        this.board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.board[i][j] = ' ';
            }
        }
    }

    /* return empty HashDictionary, select size of hash table */
    public HashDictionary createDictionary() {
        HashDictionary dict = new HashDictionary(9973);
        return dict;
    }

    /* convert 2D array, board, to string */
    private String boardToString() {
        String string = "";
        for (int i = 0; i < board_size; i++) {
            for (int j = 0; j < board_size; j++) {
                string = string + board[i][j];
            }
        }
        return string;
    }

    /* store characters of board in String, check whether String is in hashTable
     * if in hashTable, return associated score,
     * otherwise return -1
     */
    public int repeatedConfiguration(HashDictionary hashTable) {
        // convert array to string
        String config = this.boardToString();

        return hashTable.get(config);
    }

    /* represent board as a String, insert String and score in hashDictionary */
    public void addConfiguration(HashDictionary hashDictionary, int score) {
        // convert board to string
        String config = this.boardToString();

        // make Data object
        Data data = new Data(config, score);
        hashDictionary.put(data);
    }

    /* store symbol in board[row][col] */
    public void savePlay(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /* true if board[row][col] is ' ', otherwise false */
    public boolean squareIsEmpty(int row, int col) {
        if (board[row][col] == ' ') return true;
        else return false;
    }

    /* true if X-shape or +shape of length at least k formed by tiles of the kind
     * symbol on the board, k = length of x or + shape needed to win game
     */
    public boolean wins(char symbol) {

        // check for +shape of length at least k = lengthToWin formed by symbol on board
        for (int row = 0; row < board_size; row++) {    // iterates through rows
            int count = 0;
            for (int col = 0; col < board_size; col++) {
                if (board[row][col] == symbol) {
                    count ++;
                    if (count == lengthToWin && (row + lengthToWin) <= board_size) {
                        boolean cross = true;   // true = there is a valid winning +shape
                        for (int i = 1; i < lengthToWin; i++) {     // iterates through columns
                            if (board[row + i][col] != symbol) {
                                cross = false;
                                break;
                            }
                        }
                        if (cross) return true;
                    }
                }
                else count = 0;
            }
        }

        // check for X-shape of length at least k formed by symbol on board
        for (int j = 0; j < board_size; j++) {  // iterates through diagonals from top left to bottom right
            int count = 0;
            if (board[j][j] == symbol) {
                count ++;
                if (count == lengthToWin) {
                    boolean cross = true;   // true = there is a valid winning X-shape
                    for (int k = 1; k < lengthToWin; k++) {     // iterates throught diagonals from top right to bottom left
                        if (j + k >= board_size || j + k >= board_size || board[j + k][j + k] != symbol) {
                            cross = false;
                            break;
                        }
                    }
                    if (cross) return true;
                }
                else count = 0;
            }
        }
        return false;
        }

    /* true if board has no empty positions left, no player has won */
    public boolean isDraw() {
        // iterate through string and check that there is no ' '
        String config = this.boardToString();
        for (int i = 0; i < config.length(); i++) {
            if (config.charAt(i) != ' ' && this.wins('X') == false && this.wins('O') == false)
                return true;
        }
        return false;    
    }

    /* return 3 if computer wins (shape of symbol O)
     * 0 = human wins (shape X)
     * 2 = draw (no empty positions, no winner)
     * 1 = game undecided (empty positions, no winner)
     */
    public int evalBoard() {
        if (this.wins('X') == true) return 0;
        else if (this.wins('O') == true) return 3;
        else if (this.isDraw() == true) return 2;
        else return 1;
    }
}
