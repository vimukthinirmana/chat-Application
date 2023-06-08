import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static final ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();

    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(3000);
            while (true){
                System.out.println("waiting for clients... ");
                socket = serverSocket.accept();
                System.out.println("client connected successfully");

//                passed socket and client Array to ClientHandler Class and Start Thread
                ClientHandler clientThread = new ClientHandler(socket, clients);
                clients.add(clientThread);
                clientThread.start();
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}
