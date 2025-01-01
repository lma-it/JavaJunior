package client_server.lesson.task;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerExample {

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(1300);
            Socket socket = serverSocket.accept();

            OutputStream out = socket.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println("Hello!");

            socket.close();
            serverSocket.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
