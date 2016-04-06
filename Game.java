public class Game {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Board game = new Board(5);
        game.displayBoard();
    }
}
