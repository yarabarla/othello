/*
Akhilesh Yarabarla- CS 2336.001

The game class runs the main game. It first gets the board size from the user and creates the board. There is also a menu to choose computer vs player, pvp, etc. The game loop runs until there aren't any legal moves for a player or until the board is full.

To run the entire game, you just have to compile all the files and then run 'java Game'.
*/
import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        int boardSize = getBoardSize();
        Board game = new Board(boardSize);

        Player[] players = getPlayers();
        Player player1 = players[0];
        Player player2 = players[1];

        game.displayBoard();

        while(game.getLegalMoves("B").length != 0 && game.getLegalMoves("W").length != 0 && !game.isFull()) {
            player1.makeMove(game);
            game.displayBoard();
            game.printScore();
            player2.makeMove(game);
            game.displayBoard();
            game.printScore();
        }

        System.out.println("Game over");
    }

    public static Player[] getPlayers() {
        Player player1 = new HumanPlayer("B");
        Player player2 = new ComputerPlayer("W", new RandomStrategy());

        System.out.println("0: Human vs Computer");
        System.out.println("1: Human vs Human");
        System.out.println("2: Computer vs Computer");
        System.out.print("Choose the number of the mode you desire: ");
        Scanner input = new Scanner(System.in);
        int mode = input.nextInt();
        
        switch (mode) {
            case 0:
                System.out.println();
                System.out.println("0: Human is first");
                System.out.println("1: Computer is first");
                System.out.print("Choose the number of the first player you desire: ");
                int first = input.nextInt();

                switch (first) {
                    case 0:
                        player1 = new HumanPlayer("B");
                        player2 = new ComputerPlayer("W", new RandomStrategy());
                        break;
                    case 1:
                        player1 = new ComputerPlayer("B", new RandomStrategy());
                        player2 = new HumanPlayer("W");
                        break;
                }
                break;

            case 1:
                player1 = new HumanPlayer("B");
                player2 = new HumanPlayer("W");
                break;

            case 2:
                player1 = new ComputerPlayer("B", new RandomStrategy());
                player2 = new ComputerPlayer("W", new RandomStrategy());
                break;
        }

        return new Player[]{player1, player2};
    }

    public static int getBoardSize() {
        System.out.print("What size is the board: ");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        System.out.println();

        return size;

    }
}
