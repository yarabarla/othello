public class Board {
    private String[][] board;

    public Board() {};
    public Board(int size) {
        this.board = createBoard(size);
        initializeBoard(board);
    }

    public String[][] createBoard(int size) {
        String[][] board = new String[size][size];
        return board;
    }

    public void initializeBoard(String[][] board) {
        int center = board.length / 2 - 1;

        for(int row = 0; row < board.length; ++row) {
            for (int column = 0; column < board.length; ++column) {
                if ((row == center && column == center) || (row == center + 1 && column == center + 1)) {
                    board[row][column] = "W ";
                } else if ((row == center && column == center + 1) || (row == center + 1 && column == center)) {
                    board[row][column] = "B ";  
                } else {
                    board[row][column] = "_ ";
                }
            }
        }
    }

    public void displayBoard() {
        for(String[] row : this.board) {
            for(String position : row) {
                System.out.print(position);
            }
            System.out.println();
        }
    }
}
