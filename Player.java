/**
 * this class is a blueprints of player
 * 
 * @author M.Safari
 * @version 1399.01.13
 */
public class Player {
    public final House disk;
    private int diskNum;

    public Player(House disk) {
        if (disk == House.EMPTY)
            this.disk = House.WHITE;
        else
            this.disk = disk;
        diskNum = 2;
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

}