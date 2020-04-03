import java.util.Scanner;

public class Main {
    /**
     * driver code!
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("\033[34;43m\t\t\t\t\t\t  Othello \033[0m\n");
        System.out.println("\033[31;44m\t\t\t\t\t\tBy M.Safari\033[0m\n");
        Thread.sleep(2000);
        int computer = 1;
        Board gBoard = new Board();
        Player[] gPlayer = new Player[2];
        {
            gPlayer[0] = new Player(House.WHITE);
            gPlayer[1] = new Player(House.BLACK);
            gPlayer[0].setOpponent(gPlayer[1]);
            gPlayer[1].setOpponent(gPlayer[0]);
        }
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\t\t\t\t\t\033[45mEnter 2 for two players\033[0m\n\t\t\t\t    \033[45mEnter 1 for play with computer\033[0m");
        computer = sc.nextInt() % 2;
        if (computer == 0) {
            System.out.println("two player mode ...");
        } else {
            System.out.println("one player mode ...");

        }
        // initial board
        {
            gBoard.displayBoard(gPlayer[0]);
        }
        int turns = 0;
        // checking possiblity of continuing the game
        int passTurns = 0;
        // current player and disk
        Player player = gPlayer[turns % 2];
        House disk = player.disk;
        while (passTurns < 2) {
            // checking possiblity of continuing the players turn
            if (gBoard.passTurn(player)) {
                passTurns++;
                turns++;
                gBoard.displayBoard(player);
                System.out.println("pass!");
                continue;
            } else {
                passTurns = 0;
            }
            // trying to keep the game forward
            if (turns % 2 == computer) {
                AI computerPlayer = new AI(gBoard, player);
                int[] co = computerPlayer.maxScore();
                gBoard.putDisk(co[0], co[1], player);
            } else {
                int i, j;
                do {
                    i = sc.nextInt() - 1;
                    j = (int) sc.next().charAt(0) - (int) 'A';
                } while (gBoard.putDisk(i, j, player));
            }
            // showing next player board design
            {
                player = gPlayer[(turns + 1) % 2];
                disk = player.disk;
                gBoard.displayBoard(player);
            }
            turns++;
        }
        sc.close();
        int[] finalScore = new int[2];
        {
            finalScore[0] = gPlayer[0].getDiskNum();
            finalScore[1] = gPlayer[1].getDiskNum();
        }
        System.out.println("player" + ((finalScore[0] > finalScore[1]) ? " White Won!"
                : ((finalScore[0] < finalScore[1]) ? " Black Won!" : "s have equal score!")));

    }
}