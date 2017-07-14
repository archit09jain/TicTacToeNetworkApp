/**
 * Created by archit.j on 14/07/17.
 */
public interface TicTacToeInterface {

    void displayBoard();
    boolean makeMove(int dx,int dy,int player);
    boolean checkIfValidOperation(int x,int y);
    boolean checkIfGameEnds();
    int findWinner();

}
