public class CheckXShape {
    public static boolean hasXShape(char[][] board, int k) {
        int rows = board.length;
        int cols = board[0].length;

        if (k <= 0 || k > Math.min(rows, cols)) {
            return false; // Invalid value of 'k'
        }

        // Check the main diagonal (from top-left to bottom-right)
        for (int i = 0; i < rows; i++) {
            int count = 0;
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    if (count == k) {
                        // Check for an anti-diagonal
                        boolean hasAntiDiagonal = true;
                        for (int d = 1; d < k; d++) {
                            if (i + d >= rows || j + d >= cols || board[i + d][j + d] != 'X') {
                                hasAntiDiagonal = false;
                                break;
                            }
                        }
                        if (hasAntiDiagonal) {
                            return true; // Found an "X" shape of length 'k'
                        }
                    }
                } else {
                    count = 0;
                }
            }
        }

        return false; // No "X" shape found
    }

    public static void main(String[] args) {
        char[][] board = {
            {' ', ' ', ' ', 'X', ' '},
            {' ', 'X', 'X', 'X', ' '},
            {' ', ' ', 'X', ' ', ' '},
            {' ', 'X', 'X', 'X', ' '},
            {' ', ' ', ' ', 'X', ' '}
        };

        int k = 5;
        boolean hasX = hasXShape(board, k);
        if (hasX) {
            System.out.println("The board has an 'X' shape of length " + k + ".");
        } else {
            System.out.println("The board does not have an 'X' shape of length " + k + ".");
        }
    }
}
