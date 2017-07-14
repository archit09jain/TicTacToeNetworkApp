import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by archit.j on 14/07/17.
 */
public class DataSource {

    private Socket socket1,socket2;

    DataSource(Socket socket1,Socket socket2) {

        this.socket1 = socket1;
        this.socket2 = socket2;
    }


    ArrayList<Integer> performTask(Socket server) throws IOException{

        ArrayList<Integer> list = new ArrayList<>();

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

    ArrayList<Integer> getDataInput(int turn) throws IOException{

        ArrayList<Integer> list = null;

        if(turn == 0) {
            list = performTask(socket1);
        }
        else {
            list = performTask(socket2);
        }
       // System.out.println(list);
        return list;
    }

}
