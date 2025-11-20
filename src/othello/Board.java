package othello;

public class Board {
    private final int boardSize = 8;
    private char black ='B';
    private char white ='W';

    public int getBoardSize() {
        return boardSize;
    }

    private final char EMPTY =' ';
    private char board[][];
    public static final int PLAYER1WINS = 1;
    public static final int PLAYER2WINS = 2;
    //public static final int DRAW = 3;
    public static final int INCOMPLETE = 4;
    public static final int INVALIDMOVE = 5;
     public Board(){
        board = new char[boardSize][boardSize];
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                board[i][j]=EMPTY;
            }
        }
        board[3][3] = 'W';
        board[4][4] = 'W';
        board[3][4] = 'B';
        board[4][3] = 'B';

     }

    public void print(){
        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                System.out.print("| "+board[i][j]+" |");
            }
            System.out.println();
        }
    }


    public boolean move(char symbol, int row, int col) {
        if ((row < 0 || row >= boardSize) || (col < 0 || col >= boardSize) || board[row][col] != EMPTY) {
            return false;
        }

        // Directions (8 directions)
        int[] xDir = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] yDir = {0, 1, 1, 1, 0, -1, -1, -1};

        char opponent = (symbol == black) ? white : black;

        boolean valid = false;

        boolean[][] toFlip = new boolean[boardSize][boardSize];

        for (int i = 0; i < xDir.length; i++) {
            int xStep = row + xDir[i];
            int yStep = col + yDir[i];

            if (xStep < 0 || xStep >= boardSize || yStep < 0 || yStep >= boardSize) {
                continue;
            }

            if (board[xStep][yStep] != opponent) {
                continue;
            }

            int count = 0;
            int curX = xStep;
            int curY = yStep;

            while (curX >= 0 && curX < boardSize && curY >= 0 && curY < boardSize) {
                if (board[curX][curY] == opponent) {
                    count++;
                } else if (board[curX][curY] == symbol) {
                    if (count > 0) {
                        valid = true;
                        int flipX = row + xDir[i];
                        int flipY = col + yDir[i];
                        for (int j = 0; j < count; j++) {
                            toFlip[flipX][flipY] = true;
                            flipX += xDir[i];
                            flipY += yDir[i];
                        }
                    }
                    break;
                } else {
                    break;
                }
                curX += xDir[i];
                curY += yDir[i];
            }
        }

        if (!valid) {
            return false;
        }

        board[row][col] = symbol;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (toFlip[i][j]) {
                    board[i][j] = symbol;
                }
            }
        }
        return true;
    }


}
