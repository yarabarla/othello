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
        int moveIndex = generator.nextInt(legalMoves.length);
        return legalMoves[moveIndex];
    }
}
