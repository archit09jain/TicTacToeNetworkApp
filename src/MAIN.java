import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by archit.j on 14/07/17.
 */
public class MAIN {

    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(11457);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        //while(true) {


                Socket socket1 = serverSocket.accept();
                Socket socket2 = serverSocket.accept();

                executorService.submit(new Runnable() {
                @Override
                public void run() {

                    Player players[] = new Player[2];
                    players[0] = new Player("Player 1");
                    players[1] = new Player("Player 2");

                    Controller controller = new StandardTicTacToeGameController(players,socket1,socket2);

                    int gameStatus = controller.play();

                    System.out.println(gameStatus);

                    if(gameStatus == -1)
                        System.out.println("No one wins !! Something went wrong");
                    else
                        System.out.println("Winner: " + gameStatus);;

                }
            });
       // }

    }
}
