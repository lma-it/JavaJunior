package client_server.lesson.task;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientExample2 {

    public static void main(String[] args) {
        try{
            InetAddress address = InetAddress.getLocalHost();
            Socket client = new Socket(address, 1300);

            System.out.println(client.getInetAddress());
            System.out.println(client.getLocalPort());

            InputStream in = client.getInputStream();
            OutputStream out = client.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(in);
            PrintStream printStream = new PrintStream(out);

            printStream.println("Привет!");
            System.out.println(dataInputStream.readLine());
            client.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
