import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by archit.j on 14/07/17.
 */
public class DataSource {

    private ServerSocket serverSocket1,serverSocket2;
    public int PORT1 = 11390;

   // private Scanner scanner;


    DataSource(Socket serverSocket1, serverSocket2) {

        this.serverSocket1 = serverSocket1;
        this.serverSocket2 = serverSocket2;
    }


    ArrayList<Integer> performTask(Socket server) throws IOException{

        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("Just connected to " + server.getRemoteSocketAddress());

        DataOutputStream out = new DataOutputStream(server.getOutputStream());
        out.writeUTF("Your chance:");

        DataInputStream in = new DataInputStream(server.getInputStream());
        String[] data = (in.readUTF()).split(" ");

        System.out.println(data[0] + " " + data[1]);
        for (int i = 0; i < data.length; i++) {
            list.add(Integer.parseInt(data[i]));
        }
        return list;
    }

    ArrayList<Integer> getDataInput(int turn) {

        ArrayList<Integer> list = null;

        if(turn == 0) {

            //here code redundancy is there remove it later
            //while(true) {
                try {
                    Socket server = serverSocket2.accept();
                    list = performTask(server);

                   // if(list.size() !=0)
                     //       break;

                } catch (IOException e) {
                    e.printStackTrace();
                }
           // }
        }
        else {
            //getting data from the socket

          //  while(true) {
                try {
                    Socket server = serverSocket1.accept();

                    list = performTask(server);

                    //if(list.size() !=0 ){
                       // server.close();
                      //  break;
                    //}

                } catch (IOException e) {
                    e.printStackTrace();
                }
           // }
        }
        System.out.println(list);
        return list;
    }

}
