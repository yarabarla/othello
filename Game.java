public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Board game = new Board(4);
        game.displayBoard();

        ComputerPlayer player1 = new ComputerPlayer("B", new RandomStrategy());
        ComputerPlayer player2 = new ComputerPlayer("W", new RandomStrategy());

        while(game.getLegalMoves("B").length != 0 && game.getLegalMoves("W").length != 0 && !game.isFull()) {
            System.out.println("Black");
            player1.makeMove(game);
            game.displayBoard();
            System.out.println("White");
            player2.makeMove(game);
            game.displayBoard();
        }
//        ComputerPlayer player1 = new ComputerPlayer("W", new RandomStrategy());

//        player1.makeMove(game);
    }
}
