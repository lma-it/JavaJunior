package client_server.lesson.task;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientExample1 {
    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getLocalHost();
            Socket socket = new Socket(address, 1300);
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
