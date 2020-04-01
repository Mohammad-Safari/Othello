import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board gBoard = new Board();
        Player[] gPlayer = new Player[2];
        {
            gPlayer[0] = new Player(House.WHITE);
            gPlayer[1] = new Player(House.BLACK);
        }
        {
            gBoard.displayBoard();
            System.out.println("White player: " + House.WHITE.disk());
        }
        Scanner sc = new Scanner(System.in);
        int turns = 0;
        // checking possiblity of continuing the game
        int passTurns = 0;
        while (passTurns < 2) {
            Player player = gPlayer[turns % 2];
            House disk = player.disk;
            // checking possiblity of continuing the players turn
            if (gBoard.passTurn(player)) {
                passTurns++;
                turns++;
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
            // showing result on board
            {
                gBoard.displayBoard();
                System.out.println(((disk == House.WHITE) ? "Black" : "White") + " player : " + disk.disk());
            }
            turns++;
        }
        sc.close();

    }
}