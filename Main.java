import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board gBoard = new Board();
        Player[] gPlayer = new Player[2];
        {
            gPlayer[0] = new Player(House.WHITE);
            gPlayer[1] = new Player(House.BLACK);
            gPlayer[0].setOpponent(gPlayer[1]);
            gPlayer[1].setOpponent(gPlayer[0]);
        }
        // initial board
        {
            gBoard.displayBoard(gPlayer[0]);
        }
        Scanner sc = new Scanner(System.in);
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
                System.out.println("passing");
                continue;
            } else {
                passTurns = 0;
            }
            // trying to keep the game forward
            {
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
        System.out.println("player" + ((finalScore[0] > finalScore[1]) ? " White Won!" :((finalScore[0] < finalScore[1]) ?" Black Won!": "s have equal score!")));

    }
}