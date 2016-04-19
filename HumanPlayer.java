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
