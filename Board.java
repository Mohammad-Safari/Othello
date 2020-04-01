import java.util.Arrays;

/**
 * house type enumerating the only three possible values for each house in game
 * board
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

    /**
     * 
     * @return the opposite value if not empty
     */
    public House theOther() {
        if (this == WHITE)
            return BLACK;
        if (this == BLACK)
            return WHITE;
        return EMPTY;
    }
}

/**
 * this class roles as base class of othello game, containing displaying and
 * disk placement, checking rules of game implementing
 * 
 * @author M.Safari
 * @version 1399.01.10
 */
public class Board {
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
     * put disk in coordinated house if possible
     * 
     * @param i
     * @param j
     * @param disk
     * @return possiblity of puting disk in coordinated house
     */
    public boolean putDisk(int i, int j, Player player) {
        if (isEmpty(i, j))
            // thsi method checks also the range
            if (checkFlipDisks(i, j, player.disk)) {
                flipDisks(i, j, player);
                return false;
            }
        return true;
    }

    /**
     * 
     * @param i
     * @param j
     * @return
     */
    public boolean isEmpty(int i, int j) {
        if (i >= 0 && j >= 0 && i < 8 && j < 8)
            return (board[i][j] == House.EMPTY);
        return false;
    }

    /**
     * moving through 8 , flipping opponents disks
     * 
     * @param i
     * @param j
     * @param disk
     * @return
     */
    public void flipDisks(int i, int j, Player player) {
        for (int iStep = -1; iStep <= 1; iStep++)
            for (int jStep = -1; jStep <= 1; jStep++) {
                if (iStep == 0 && jStep == 0)
                    continue;
                int[] destination = checkDirection(i, j, iStep, jStep, player.disk);
                if (!Arrays.equals(destination, new int[] { -1, -1 })) {
                    // this also put disk beside flipping beseiged disks
                    for (int a = i, b = j; ((a != destination[0]) || (b != destination[1])); a += iStep, b += jStep) {
                        board[a][b] = player.disk;
                        player.increaseDisks();
                    }
                }

            }
    }

    /**
     * moving through 8 , investigating possibility to flip opponents disks
     * 
     * @param i
     * @param j
     * @param disk
     * @return
     */
    public boolean checkFlipDisks(int i, int j, House disk) {
        for (int iStep = -1; iStep <= 1; iStep++)
            for (int jStep = -1; jStep <= 1; jStep++) {
                if (iStep == 0 && jStep == 0)
                    continue;
                if (!Arrays.equals(checkDirection(i, j, iStep, jStep, disk), new int[] { -1, -1 }))
                    return true;
            }
        return false;

    }

    /**
     * getting an empty House to check whether is it possible to flip opponents disk
     * and returning the house that completes opponents disk beseiging, {-1, -1}
     * means that seige is not possible in this direction
     * 
     * @param i
     * @param j
     * @param iStep
     * @param jStep
     * @param disk
     * @return
     */
    public int[] checkDirection(int i, int j, int iStep, int jStep, House disk) {
        for (int a = i + iStep, b = j + jStep; ((a < 8 && a >= 0) && (b < 8 & b >= 0)); a += iStep, b += jStep)
            if (board[a][b] == House.EMPTY) {
                break;
            } else if (board[a][b] == disk) {
                // meaning if there is any opposite color to beseige
                if (board[a - iStep][b - jStep] == disk.theOther())
                    return new int[] { a, b };
                else
                    // meaning that in this direction two same color are adjacent
                    break;
            }
        return new int[] { -1, -1 };
    }

    /**
     * investigateing whether the house is suitable for disk placement according to
     * disk
     * 
     * @param i
     * @param j
     * @param disk
     * @return
     */
    public boolean checkHouse(int i, int j, House disk) {
        // first check that the house must be empty
        if (isEmpty(i, j))
            // then neighbor houses should be checked if there is any adjacent disk to make
            // it probable to place disk
            for (int a = -1; a <= 1; a++)
                for (int b = -1; b <= 1; b++)
                    if (!isEmpty(i + a, j + b)) {
                        // then let the algorithm of game rule be executed(now on probable houses(which
                        // have adjacent disk) so that algorithm does not waste our time)
                        if (checkFlipDisks(i, j, disk))
                            return true;
                    }
        return false;
    }

    public boolean passTurn(Player player) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (checkHouse(i, j, player.disk))
                    return false;
        return true;
    }

}