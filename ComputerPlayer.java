import java.util.Random;
public class ComputerPlayer extends Player {
    private ChoosingStrategy strategy;

    ComputerPlayer() {};
    ComputerPlayer(String color, ChoosingStrategy strategy) {
        super(color);
        this.strategy = strategy;
    }

    public void makeMove(Board board) {
        Integer[] newMove = strategy.getMove(board);
        move(newMove, board);
    }
}

interface ChoosingStrategy {
    public Integer[] getMove(Board board);
}

class RandomStrategy implements ChoosingStrategy {
    @Override
    public Integer[] getMove(Board board) {
        Random generator = new Random();
        Integer[][] legalMoves = board.getLegalMoves();
        int moveIndex = generator.nextInt(legalMoves.length);
        System.out.println("Random: " + moveIndex);
        return legalMoves[moveIndex];
    }
}
