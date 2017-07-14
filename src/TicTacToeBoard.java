import java.util.Scanner;

/**
 * Created by archit.j on 14/07/17.
 */
public class TicTacToeBoard implements TicTacToeInterface{

        private Integer[][] gameState;
        private int rows = 3;
        private int cols = 3;
        private int winner = 0;

        private Player[] players;

        TicTacToeBoard(Player players[]) {
            gameState = new Integer[rows][cols];

            for(int i = 0;i<rows;i++)
                    for(int j = 0;j<cols;j++)
                            gameState[i][j] = -1;
            players = this.players;

        }


        @Override
        public void displayBoard() {

                for(int i = 0;i<rows;i++) {
                        System.out.println("");
                        for(int j = 0;j<3;j++) {
                                if(gameState[i][j] == -1)
                                        System.out.print(" - ");
                                else if(gameState[i][j] == 1)
                                        System.out.print(" X ");
                                else
                                        System.out.print(" O ");
                        }
                }
        }

        @Override
        public boolean makeMove(int dx, int dy, int player) {

                if(checkIfValidOperation(dx,dy) == false)
                                return false;

                gameState[dx][dy] = player;
                return true;
        }

        @Override
        public boolean checkIfValidOperation(int x, int y) {
                if(x>=0 && x<rows && y>=0 && y<cols && gameState[x][y] == -1)
                                return true;
                else
                                return false;

        }

        private boolean checkIfRowsAreComplete() {

                for(int i = 0;i<rows;i++) {
                        int j = 1;
                        while(j<cols && gameState[i][j] == gameState[i][j-1] && gameState[i][j]!=-1)
                                j++;
                        if(j == cols) {
                                winner = gameState[i][0];
                                return true;
                        }

                }
                return false;
        }

        private boolean checkIfColsAreComplete() {
                for(int j = 0;j<cols;j++) {
                        int i = 1;
                        while(i<rows && gameState[i][j] == gameState[i-1][j] && gameState[i][j]!=-1)
                                i++;


                        if(i == rows) {
                                winner = gameState[0][i];
                                return true;
                        }
                }
                return false;
        }

        @Override
        public boolean checkIfGameEnds() {

                if(checkIfRowsAreComplete() || checkIfColsAreComplete())
                        return true;

                //diagnol's check

                int i = 1;
                for(i = 1;i<rows;i++) {
                      if(gameState[i][i]!=-1 && gameState[i-1][i-1] == gameState[i][i])
                             continue;
                      else
                        break;
                }
                if(i == rows) {
                        winner = gameState[0][0];
                        return true;
                }


                i = 1;
                for(i = 1;i<rows;i++) {
                        //System.out.println(i);
                        if(gameState[i][cols-i-1]!=-1 && gameState[i-1][cols-i] == gameState[i][cols-i-1])
                                continue;
                        else
                                break;
                }
                if(i == rows) {

                        winner = gameState[rows-1][0];
                        return true;
                }

                return false;
        }


        @Override
        public int findWinner() {
                return winner;
        }
}
