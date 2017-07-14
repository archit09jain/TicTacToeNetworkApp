import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by archit.j on 14/07/17.
 */
public class StandardTicTacToeGameController implements Controller{

    private Player players[];
    private DataSource dataSource;
    private int turn = 0;
    private ServerSocket serverSocket1,serverSocket2;


    StandardTicTacToeGameController(Player players[],ServerSocket serverSocket1,ServerSocket serverSocket2) {
        this.players = players;
        System.out.println(players);
        this.serverSocket1 = serverSocket1;
        this.serverSocket2 = serverSocket2;
        dataSource = new DataSource(serverSocket1,serverSocket2);

    }



    private TicTacToeGameFactory factory  = TicTacToeGameFactory.getFactoryInstance();
    private TicTacToeInterface game = factory.getGameObject("StandardTicTacToe",players);

    @Override
    public int play() {
        //enter the code for game simulator

        game.displayBoard();
        System.out.println("");

        System.out.println("Player : " + players[turn].getName());
        System.out.println("Enter the coordinates: ");



        ArrayList<Integer> list = dataSource.getDataInput(turn);

        int x = list.get(0);
        int y = list.get(1);

        if(game.makeMove(x,y,turn) == false) {
            System.out.println("Wrong Input");
            return -1;
        }

        if(game.checkIfGameEnds())
                return game.findWinner();

        turn^=1;
        return play();
    }
}
