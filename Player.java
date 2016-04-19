/*
Akhilesh Yarabarla- CS 2336.001

The abstract player class can be inherited by either a computer player or a human player. It has a method move() that takes a coordinate and check to see if it is a valid move. If so, it applies the move to the board and flips any intermediate squares as well. The abstract method makeMove() is used by subclasses to implement how they choose their moves.
*/
import java.util.Arrays;
public abstract class Player {
    public String color;

    public Player(){};
    public Player(String color) {
        this.color = color;
    }

    protected boolean move(Integer[] coordinates, Board board) {
        boolean moveValid = false;
        Integer[][] legalMoves = board.getLegalMoves(this.color);

        for (Integer[] move : legalMoves) {
            boolean isLegalMove = move[0] == coordinates[0] && move[1] == coordinates[1];
            if (isLegalMove) {
                board.changePosition(coordinates, this.color);
                System.out.println("\nSuccess: " + this.color + " move at " + Arrays.toString(coordinates) + "\n");
                Integer[][] flips = board.getFlips(coordinates);

                for (Integer[] flip : flips) {
                    board.changePosition(flip, this.color);
                }
                moveValid = true;
            }
        }

        if (!moveValid) {
            System.out.println("Invalid move. ");
        }

        return moveValid;
    }

    public abstract void makeMove(Board board);
}
