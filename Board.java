import java.util.Arrays;
import java.util.ArrayList;
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
                    board[row][column] = "W";
                } else if ((row == center && column == center + 1) || (row == center + 1 && column == center)) {
                    board[row][column] = "B";
                } else {
                    board[row][column] = "_";
                }
            }
        }
    }

    public void changePosition(Integer[] coordinates, String newPiece) {
        this.board[coordinates[0]][coordinates[1]] = newPiece;
    }

    public Integer[][] getLegalMoves(String color) {
//        Integer[][] legalMoves = {{0,0}, {2, 3}, {0, 1}};
        ArrayList<Integer[]> legalList = new ArrayList<Integer[]>();
/*        for (int row = 0; row < this.board.length; ++row) {
            for (int column = 0; column < this.board.length; ++column) {
                if (this.board[row][column] == "_") {

                }
            }
        }*/
        int[] mover = {2, 2};
        Searcher searcher = new Searcher(color, this);
        searcher.checkAxes(mover);
        Integer[] move = {new Integer(0), new Integer(0)};
        legalList.add(move);
        legalList.add(new Integer[]{new Integer(5), new Integer(4)});

        Integer[][] legalMoves = new Integer[legalList.size()][legalList.size()];
        legalMoves = legalList.toArray(legalMoves);
/*        for (Integer[] item : legalMoves) {
            for(Integer num : item) {
                System.out.print(num);
            }
            System.out.println();
        }*/
        return legalMoves;
    }

    public String[][] getBoardStructure() {
        return this.board;
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

class Searcher {
    private String color;
    private String[][] board;

    Searcher(){};
    Searcher(String color, Board board) {
        this.color = color;
        this.board = board.getBoardStructure();
    }

    public ArrayList<Integer[]> checkAxes(int[] coordinate) {
        
        ArrayList<Integer[]> verticalMoves = checkVerticalAxis(coordinate);
/*        for(Integer[] i : verticalMoves) {
            System.out.println(Arrays.toString(i));
        }*/
        return verticalMoves;
    }

    private ArrayList<Integer[]> checkVector(String piece, Integer[][] vector) {
        ArrayList<Integer[]> validMoves = new ArrayList<Integer[]>();

        for (int coorIndex = 0; coorIndex < vector.length; ++coorIndex) {
            Integer[] currentPos = vector[coorIndex];
            String currentPiece = this.board[currentPos[0]][currentPos[1]];

            if (currentPiece == piece) {
                break;
            }

            if (currentPiece == "_") {
                if (coorIndex != 0) {
                    validMoves.add(currentPos);
                }

                break;
            }
        }

        return validMoves;
    }

    private ArrayList<Integer[]> checkVerticalAxis(int[] coordinate) {
        int size = this.board.length;
        int columnIndex = coordinate[1];
        String piece = this.board[coordinate[0]][coordinate[1]];
        Integer[][] upper = new Integer[coordinate[0]][2];
        Integer[][] lower = new Integer[size - coordinate[0] - 1][2];

        for (int row = 0; row < size; ++row) {
            Integer[] coor = {row, columnIndex};

            if (row < coordinate[0]) {
                upper[upper.length - row - 1] = coor;
            } else if (row > coordinate[0]) {
                lower[row - upper.length - 1] = coor;
            }
        }

        ArrayList<Integer[]> upperValidMoves = checkVector(piece, upper);
        ArrayList<Integer[]> lowerValidMoves = checkVector(piece, lower);
       
        upperValidMoves.addAll(lowerValidMoves);
        return upperValidMoves;
    }

/*    private ArrayList<Integer[]> checkVerticalAxis(int[] coordinate) {
        String coorLocation = this.board[coordinate[0]][coordinate[1]];
        int size = this.board.length;
        int columnIndex = coordinate[1];
        String[] vertical = new String[size];
        String[] upperVertical = new String[coordinate[0] + 1];
        String[] lowerVertical = new String[size - coordinate[0] + 1];
        ArrayList<Integer[]> validMoves = new ArrayList<Integer[]>();

        for (int row = 0; row < size; ++row) {
            vertical[row] = this.board[row][columnIndex];
        }

        for (int row = 0; row < size; ++row) {
            if (row < coordinate[0]) {
                upperVertical[row] = vertical[row];
            } else if (row > coordinate[0]) {
                lowerVertical[row - upperVertical.length] = vertical[row];
            }
        }

        for (int row = upperVertical.length - 1; row >= 0; --row) {
            if (upperVertical[row] == coorLocation) {
                break;
            }

            if (upperVertical[row] == "_") {
                if (row != upperVertical.length - 1) {
                    Integer[] move = {new Integer(row), new Integer(columnIndex)};
                    validMoves.add(move);
                }

                break;
            }
        }

        for (int row = 0; row < lowerVertical.length; ++row) {
            if (lowerVertical[row] == coorLocation) {
                break;
            }

            if (lowerVertical[row] == "_") {
                if (row != 0) {
                    Integer[] move = {new Integer(row + upperVertical.length), new Integer(columnIndex)};
                    validMoves.add(move);
                }

                break;
            }
        }

        return validMoves;
    }*/
}
