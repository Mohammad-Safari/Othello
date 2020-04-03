import java.util.*;

/**
 * this class is intended to be an ai player; choosing best possible place for
 * disk to make player disks max in each turn.
 * 
 * @author M.Safari
 * @version 1399.01.14
 */
public class AI {
    private Player player;
    private Board origin;
    private Map<Board, int[]> possibleChoices;

    /**
     * 
     */
    public AI(Board origin, Player player) {
        this.origin = origin;
        this.player = player; 
        possibleChoices = new HashMap<Board, int[]>();
        {
            for (int i = 7; i >= 0; i--)
                for (int j = 7; j >= 0; j--)
                    if (origin.checkHouse(i, j, player.disk))
                        possibleChoices.put(new Board(origin), new int[] { i, j, 0 });
        }
    }

    /**
     * 
     */
    public int playTurns(Board board, int i, int j) {
        Player player = new Player(this.player.disk, this.player.getDiskNum());
        player.setOpponent(new Player(this.player.getOpponent().disk, this.player.getOpponent().getDiskNum()));
        board.putDisk(i, j, player);
        return board.Score(player);
    }

    /**
     * 
     */
    public void setScore() {
        for (Board check : possibleChoices.keySet()) {
            int[] numSet = possibleChoices.get(check);
            numSet[2] = playTurns(check, numSet[0], numSet[1]);
        }
    }

    /**
     * 
     */
    public int[] maxScore() {
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
        return new int[] { iMax, jMax };
    }
}