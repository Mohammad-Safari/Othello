import java.util.*;

public class Main {
    /**
     * driver code!
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        // title
        System.out.println("\033[34;43m\t\t\t\t\t\t  Othello \033[0m\n");
        System.out.println("\033[31;44m\t\t\t\t\t\tBy M.Safari\033[0m\n");
        Thread.sleep(2000);
        // creating game board
        Board gBoard = new Board();
        // number of players
        int playerNum = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\t\t\t\t\t\033[45mEnter 2 for two-player\033[0m\n\t\t\t\t\t\033[45mEnter 1 for one-player\033[0m");
        do {
            playerNum = sc.nextInt();
        } while (playerNum != 1 && playerNum != 2);
        // creating players
        Player[] gPlayer = new Player[2];
        {
            gPlayer[0] = new Player(House.WHITE);
            gPlayer[1] = ((playerNum == 1) ? new AI(House.BLACK) : new Player(House.BLACK));
            gPlayer[0].setOpponent(gPlayer[1]);
            gPlayer[1].setOpponent(gPlayer[0]);
        }
        if (playerNum == 1) {
            System.out.println("One-Player Mode ...");
        } else {
            System.out.println("Two-Player Mode ...");

        }
        // checking possiblity of continuing the game
        int passTurns = 0;
        // current player and disk
        Player player = gPlayer[0];
        House disk = player.disk;
        // initial board
        {
            gBoard.displayBoard(player, -1, -1);
        }
        while (passTurns < 2) {
            // checking possiblity of continuing the players turn
            if (gBoard.passTurn(player)) {
                passTurns++;
                player = player.getOpponent();
                // next board for oppnent
                gBoard.displayBoard(player, -1, -1);
                System.out.println("pass!");
                continue;
            } else {
                passTurns = 0;
            }
            // trying to keep the game forward
            int i, j;
            if (player instanceof AI) {
                int[] co = ((AI) player).maxScore(gBoard);
                i = co[0];
                j = co[1];
            } else {
                do {
                    i = sc.nextInt() - 1;
                    j = (int) sc.next().charAt(0) - (int) 'A';
                } while (gBoard.putDisk(i, j, player));
            }
            // showing next player board design
            {
                player = player.getOpponent();
                disk = player.disk;
                gBoard.displayBoard(player, i, j);
            }
            Thread.sleep(500);
        }
        sc.close();
        int[] finalScore = new int[2];
        {
            finalScore[0] = gPlayer[0].getDiskNum();
            finalScore[1] = gPlayer[1].getDiskNum();
        }
        // final result
        System.out.println("player" + ((finalScore[0] > finalScore[1]) ? " White Won!"
                : ((finalScore[0] < finalScore[1]) ? " Black Won!" : "s have equal score!")));

    }
}