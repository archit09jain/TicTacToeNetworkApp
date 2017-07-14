import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by archit.j on 14/07/17.
 */
public class MAIN {

    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket1 = new ServerSocket(11399);
        ServerSocket serverSocket2 = serverSocket1;

        ExecutorService executorService = Executors.newCachedThreadPool();
        while(true) {


                Scanner scan  = new Scanner(System.in);

                String name1 = scan.nextLine();
                String name2 = scan.nextLine();

                executorService.submit(new Runnable() {
                @Override
                public void run() {

                    Player players[] = new Player[2];
                    players[0] = new Player(name1);
                    players[1] = new Player(name2);


                    Controller controller = new StandardTicTacToeGameController(players,serverSocket1,serverSocket2);

                    int gameStatus = controller.play();

                    System.out.println(gameStatus);

                    if(gameStatus == -1)
                        System.out.println("No one wins !! Something went wrong");
                    else
                        System.out.println("Winner: " + gameStatus);;
                }
            });
        }

    }
}
