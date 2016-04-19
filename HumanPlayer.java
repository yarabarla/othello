/*
Akhilesh Yarabarla- CS 2336.001

HumanPlayer is a subclass of Player. It's implemented makeMove() method gets user input for a move and attempts to actually move the piece on the board by calling move(). User input is taken until valid input is received.
*/
import java.util.Scanner;
public class HumanPlayer extends Player {
    HumanPlayer() {};
    HumanPlayer(String color) {
       super(color); 
    }

    public void makeMove(Board board) {

        boolean moveValid = false;
        while (!moveValid) {
            if (board.getLegalMoves(this.color).length == 0) {
                return;
            }

            Integer[] newMove = new Integer[2];
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your move coordinates, row then column, space separated: ");

            for(int i = 0; i < 2; ++i) {
                newMove[i] = input.nextInt();
            }

            moveValid = move(newMove, board);
        }
    }

}
