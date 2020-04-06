import java.util.*;

/**
 * this class is intended to act as an ai player decide according to final
 * score; choosing best possible place for disk to make player disks max in each
 * turn.
 * 
 * @author M.Safari
 * @version 1399.01.14
 */
public class AI extends Player {
    private Board origin;
    private Map<Board, int[]> possibleChoices; // map of boards and board parameters such as selected indexes for
                                               // placement and final score

    /**
     * extending an AI player from Player Class
     */
    public AI(House disk) {
        super(disk);
    }

    /**
     * cloning as many boards as possible current choices according to given board
     */
    private void setPossibleChoices(Board origin) {
        this.origin = origin;
        possibleChoices = new HashMap<Board, int[]>();
        {
            for (int i = 7; i >= 0; i--)
                for (int j = 7; j >= 0; j--)
                    if (origin.checkHouse(i, j, this.disk))
                        possibleChoices.put(new Board(origin), new int[] { i, j, 0 });
        }
    }

    /**
     * implementing each possible placement on each cloned board on unreal cloned
     * players
     */
    private int playTurns(Board board, int i, int j) {
        // cloning a player
        Player player = new Player(this.disk, this.getDiskNum());
        player.setOpponent(new Player(this.getOpponent().disk, this.getOpponent().getDiskNum()));
        board.putDisk(i, j, player);
        return board.Score(player);
    }

    /**
     * sets score in each boards dedicated parameter for final score
     */
    private void setScore() {
        for (Board check : possibleChoices.keySet()) {
            int[] numSet = possibleChoices.get(check);
            numSet[2] = playTurns(check, numSet[0], numSet[1]);
        }
    }

    /**
     * placing disk with maximum final score and return the highest score-producing indexes for
     * final placement
     */
    public int[] maxScore(Board origin) {
        setPossibleChoices(origin);
        setScore();
        int max = -1;
        int iMax = 0;
        int jMax = 0;
        for (int[] set : possibleChoices.values())
            if (set[2] >= max) {
                iMax = set[0];
                jMax = set[1];
                max = set[2];
            }
        origin.putDisk(iMax, jMax, this);
        return new int[] { iMax, jMax };
    }
}