import java.util.Scanner;
public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Board game = new Board(5);

//        Player player1 = new ComputerPlayer("B", new RandomStrategy());
//        Player player1 = new HumanPlayer("B");
//        Player player2 = new HumanPlayer("W");
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
}
