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
        System.out.println(color + " legalMovesLength: " + legalMoves.length);
        if (legalMoves.length == 0) {
            Integer[] noMove = {new Integer(-1), new Integer(-1)};
            return noMove;
        }
        int moveIndex = generator.nextInt(legalMoves.length);
        return legalMoves[moveIndex];
    }
}
