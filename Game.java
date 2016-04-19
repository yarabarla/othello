public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Board game = new Board(4);
        game.displayBoard();

        ComputerPlayer player1 = new ComputerPlayer("B", new RandomStrategy());
        ComputerPlayer player2 = new ComputerPlayer("W", new RandomStrategy());

        while((game.getLegalMoves("W").length != 0 || game.getLegalMoves("B").length != 0) && !game.isFull()) {
            player1.makeMove(game);
            game.displayBoard();
            player2.makeMove(game);
            game.displayBoard();

        }
//        ComputerPlayer player1 = new ComputerPlayer("W", new RandomStrategy());

//        player1.makeMove(game);
    }
}
