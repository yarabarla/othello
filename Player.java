import java.util.Arrays;
public abstract class Player {
    public String color;

    public Player(){};
    public Player(String color) {
        this.color = color;
    }

    protected void move(Integer[] coordinates, Board board) {
        boolean moveValid = false;
        Integer[][] legalMoves = board.getLegalMoves(this.color);
        Integer[][] flips = board.getFlips(coordinates);

        for (Integer[] move : legalMoves) {
            if (move[0] == coordinates[0] && move[1] == coordinates[1]) {
                board.changePosition(coordinates, this.color);
                for (Integer[] flip : flips) {
                    board.changePosition(flip, this.color);
                }
                moveValid = true;
            }
        }

        if (!moveValid) {
            System.out.println("Invalid move. ");
        }
    }

    public abstract void makeMove(Board board);
}
