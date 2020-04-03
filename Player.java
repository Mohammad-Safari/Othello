/**
 * this class is a blueprints of player
 * 
 * @author M.Safari
 * @version 1399.01.13
 */
public class Player {
    public final House disk;
    private int diskNum;
    private Player opponent;

    public Player(House disk) {
        if (disk == House.EMPTY)
            this.disk = House.WHITE;
        else
            this.disk = disk;
        diskNum = 2;
        // opponent should be set later
    }

    /**
     * cloning a player
     * 
     * @param player
     */
    public Player(House disk, int diskNum) {
        this.disk = disk;
        this.diskNum = diskNum;
        // opponent should be set later
    }

    /**
     * @return the diskNum
     */
    public int getDiskNum() {
        return diskNum;
    }

    /**
     * 
     */
    public void increaseDisks() {
        diskNum++;
    }

    /**
     * 
     */
    public void decreaseDisks() {
        diskNum--;
    }

    /**
     * @param opponent the opponent to set
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * @return the opponent
     */
    public Player getOpponent() {
        return opponent;
    }

}