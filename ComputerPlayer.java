/*
Akhilesh Yarabarla- CS 2336.001

ComputerPlayer is a subclass of Player. It uses the Strategy pattern, so in the constructor, the desired strategy to make a move is taken from the user. The parameter is an interface so each desired strategy must be a class that implements the ChoosingStrategy interface. Currently the only strategy is picking randomly from a list of legal moves. 
*/
import java.util.Random;
public class ComputerPlayer extends Player {
    private ChoosingStrategy strategy;

    ComputerPlayer() {};
    ComputerPlayer(String color, ChoosingStrategy strategy) {
        super(color);
        this.strategy = strategy;
    }

    public void makeMove(Board board) {
        Integer[] newMove = strategy.getMove(board, this.color);
        if (newMove[0] == -1 && newMove[1] == -1) {
            return;
        }
        move(newMove, board);
    }
}

interface ChoosingStrategy {
    public Integer[] getMove(Board board, String color);
}

class RandomStrategy implements ChoosingStrategy {
    @Override
    public Integer[] getMove(Board board, String color) {
        Random generator = new Random();
        Integer[][] legalMoves = board.getLegalMoves(color);
        if (legalMoves.length == 0) { // Returns {-1, -1} to fulfill the return type
            Integer[] noMove = {new Integer(-1), new Integer(-1)};
            return noMove;
        }
        int moveIndex = generator.nextInt(legalMoves.length);
        return legalMoves[moveIndex];
    }
}
