import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by archit.j on 14/07/17.
 */
public class StandardTicTacToeGameController implements Controller{

    private Player players[];
    private DataSource dataSource;
    private int turn = 0;
    private Socket socket1,socket2;


    StandardTicTacToeGameController(Player players[], Socket socket1, Socket socket2) {
        this.players = players;
        System.out.println(players);
        this.socket1 = socket1;
        this.socket2 = socket2;
        dataSource = new DataSource(socket1,socket2);
    }



    private TicTacToeGameFactory factory  = TicTacToeGameFactory.getFactoryInstance();
    private TicTacToeInterface game = factory.getGameObject("StandardTicTacToe",players);

    @Override
    public int play() {

        game.displayBoard();
        System.out.println("");

        System.out.println("Player : " + players[turn].getName());
        System.out.println("Enter the coordinates: ");


        ArrayList<Integer> list = null;
        try {
            list = dataSource.getDataInput(turn);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
