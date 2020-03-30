import java.util.Arrays;

/**
 * 
 */
enum House {
    EMPTY, BLACK, WHITE;

    public String disk() {
        switch (this) {
            case WHITE:
                return "\u26AA ";
            case BLACK:
                return "\u26AB ";
            default:
                return "\u25EF  ";
        }
    }
}

/**
 * 
 */
class Board {
    // an array containing the board houses
    private House[][] board;

    public Board() {
        board = new House[8][8];
        {
            for (int i = 0; i < 8; i++) {
                board[i] = new House[8];
                Arrays.fill(board[i], House.EMPTY);
            }
            board[3][3] = House.BLACK;
            board[4][4] = House.BLACK;
            board[3][4] = House.WHITE;
            board[4][3] = House.WHITE;
        }
    }

    /**
     * displaying baord simply with ⚫ ⚪ ◯ charactars defined in house type
     */
    public void displayBoard() {
        // i showing each row, j showing each item in each row
        // first row the alphabet(mapping column)
        for (int j = 0; j < 8; j++)
            System.out.printf("%3s", (char) ((int) ('A') + j));
        System.out.println();
        // first column the numbers(mapping each row)
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < 8; j++)
                System.out.print(board[i][j].disk());
            System.out.println();
        }
    }

    /**
     * 
     * @param i
     * @param j
     * @param disk
     * @return
     */
    public boolean putDisk(int i, int j, House disk) {
        if (isEmpty(i, j)) {
            board[i][j] = disk;
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isEmpty(int i, int j) {
        return (board[i][j] == House.EMPTY);
    }

    public static void main(String[] args) {
        Board test = new Board();
        test.displayBoard();
    }

}