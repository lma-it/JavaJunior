package client_server.lesson.task2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final ServerSocket serverSocket;
    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void runServer(){
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("Подключен новый клиент!");
                ClientManager client = new ClientManager(socket);
                Thread thread = new Thread(client);
                thread.start();
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
            closeSocket();
        }
    }

    public void closeSocket(){
        try{
            if(serverSocket != null) serverSocket.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(1300);
            Server server = new Server(serverSocket);
            server.runServer();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
