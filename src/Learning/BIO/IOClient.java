package Learning.BIO;

import java.net.Socket;
import java.util.Date;

public class IOClient {
    public static void main(String[] args) {
        new Thread(() ->{
            try {
                Socket socket = new Socket("localhost", 6666);
                while (true){
                    socket.getOutputStream().write((new Date() + "client").getBytes());
//                    Thread.sleep(1000);
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
