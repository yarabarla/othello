/*
Akhilesh Yarabarla- CS 2336.001
The public Board class is a representation of the game board. Two methods create and initialize the starting position of the board.  Note: it can handle odd sized boards. The changePositions() method provides a public way of mutating the board. There are other utility methods that display the board, get the score, etc. The getLegalMoves and getFlips methods both use a searcher object to search the board for the desired list of coordinates. The search class has methods that call a function to get a list of vectors, which are paths radiating outward from a given coordinate. These vectors can be used by functions to get legal moves or to calculate which pieces need to be flipped from a given coordinate.
*/
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
        board[coordinates[0]][coordinates[1]] = newPiece;
    }

    public Integer[][] getLegalMoves(String color) {
        ArrayList<Integer[]> legalList = new ArrayList<Integer[]>();
        Searcher searcher = new Searcher(color, this);
        legalList = searcher.findLegalMoves();

        Integer[][] legalMoves = new Integer[legalList.size()][legalList.size()];
        legalMoves = legalList.toArray(legalMoves);

        return legalMoves;
    }

    public Integer[][] getFlips(Integer[] coor) {
        String piece = board[coor[0]][coor[1]];
        ArrayList<Integer[]> flips = new ArrayList<Integer[]>();
        Searcher searcher = new Searcher(piece, this);
        flips = searcher.getFlippablePieces(coor);

        Integer[][] validFlips = new Integer[flips.size()][flips.size()];
        validFlips = flips.toArray(validFlips);
        return validFlips;
    }

    public String[][] getBoardStructure() {
        return board;
    }

    public boolean isFull() {
        for (String[] row : board) {
            for (String position : row) {
                if (position == "_") {
                    return false;
                }
            }
        }
        return true;
    }

    public void displayBoard() {
        for (String[] row : board) {
            for (String position : row) {
                System.out.print(position);
            }
            System.out.println();
        }
    }

    private int[] getScore() {
        int whiteCount = 0; 
        int blackCount = 0;
        for (String[] row : board) {
            for (String position : row) {
                if (position == "W") {
                    whiteCount++;
                } else if (position == "B") {
                    blackCount++;
                }
            }
        }

        return new int[]{blackCount, whiteCount};
    }

    public void printScore() {
        int[] score = getScore();
        System.out.println("\nScore: Black: " + score[0] + ", White: " + score[1]);
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

    public ArrayList<Integer[]> findLegalMoves() {
    // Iterate through board to find all the pieces that belong to the current player.
    // The resulting coordinates are used to actually get the list of valid moves.
        ArrayList<Integer[]> thisPieceList = new ArrayList<Integer[]>();

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board.length; ++col) {
                Integer[] coor = new Integer[]{new Integer(row), new Integer(col)};
                if (board[row][col] == color) {
                    thisPieceList.addAll(checkAxes(coor));
                }
            }
        }

        return thisPieceList;
    }

    private ArrayList<Integer[]> checkAxes(Integer[] coordinate) {
    // Checks each axis radiating from the coordinate for valid moves and returns the
    // entire list of legal moves.
        String piece = board[coordinate[0]][coordinate[1]];

        ArrayList<Integer[][]> vectors = new ArrayList<Integer[][]>();
        vectors = getVectors(coordinate);
        ArrayList<Integer[]> validMoves = new ArrayList<Integer[]>();

        for(Integer[][] vector : vectors) {
            validMoves.addAll(checkForValidMoves(piece, vector));
        }

        return validMoves;
    }


    public ArrayList<Integer[]> getFlippablePieces(Integer[] coordinate) {
    // Finds all the pieces that need to be flipped after a move
        String piece = board[coordinate[0]][coordinate[1]];

        ArrayList<Integer[][]> vectors = new ArrayList<Integer[][]>();
        vectors = getVectors(coordinate);
        ArrayList<Integer[]> validFlips = new ArrayList<Integer[]>();

        for(Integer[][] vector : vectors) {
            validFlips.addAll(checkForFlips(piece, vector));
        }

        return validFlips;
    }

    private ArrayList<Integer[][]> getVectors(Integer[] coordinate) {
    // Fills up each vector that lead out from a coordinate with index 0 being the 
    // closest to the given coordinate
        int size = board.length;
        int rowIndex = coordinate[0];
        int columnIndex = coordinate[1];

        Integer[][] left = new Integer[columnIndex][2];
        Integer[][] right = new Integer[size - columnIndex - 1][2];
        Integer[][] upper = new Integer[rowIndex][2];
        Integer[][] lower = new Integer[size - rowIndex - 1][2];

        for (int i = 0; i < size; ++i) {
            Integer[] horizontalCoor = {rowIndex, i};
            Integer[] verticalCoor = {i, columnIndex};

            if (i < columnIndex) {
                left[left.length - i - 1] = horizontalCoor;
            } else if (i > columnIndex) {
                right[i - columnIndex - 1] = horizontalCoor;
            }

            if (i < rowIndex) {
                upper[upper.length - i - 1] = verticalCoor;
            } else if (i > rowIndex) {
                lower[i - rowIndex - 1] = verticalCoor;
            }
        }
        
        ArrayList<Integer[][]> vectors = new ArrayList<Integer[][]>();
        vectors.add(left);
        vectors.add(right);
        vectors.add(upper);
        vectors.add(lower);

        return vectors;
    }

    private ArrayList<Integer[]> checkForValidMoves (String piece, Integer[][] vector) {
    // Finds all the valid moves for a vector
        ArrayList<Integer[]> validMoves = new ArrayList<Integer[]>();

        for (int coorIndex = 0; coorIndex < vector.length; ++coorIndex) {
            Integer[] currentPos = vector[coorIndex];
            String currentPiece = board[currentPos[0]][currentPos[1]];

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

    private ArrayList<Integer[]> checkForFlips(String piece, Integer[][] vector) {
    // Finds all the valid flips for a vector and move coordinate
        ArrayList<Integer[]> flipMoves = new ArrayList<Integer[]>();

        for (int coorIndex = 0; coorIndex < vector.length; ++coorIndex) {
            Integer[] currentPos = vector[coorIndex];
            String currentPiece = board[currentPos[0]][currentPos[1]];

            if (currentPiece == "_") {
                if (coorIndex != 0) {
                    Integer[] previousPos = vector[coorIndex - 1];
                    String previousPiece = board[previousPos[0]][previousPos[1]];

                    if (previousPiece == piece) {
                        return flipMoves;
                    }
                }
                return new ArrayList<Integer[]>();
            } else if (currentPiece == piece) {
                break;
            } else if (coorIndex == vector.length - 1) {
                return new ArrayList<Integer[]>();
            } else {
                flipMoves.add(currentPos);
            }
        }

        return flipMoves;
    }
}
