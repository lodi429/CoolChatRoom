import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static void main(){
        try(ServerSocket serverSock = new ServerSocket(9999)){
            System.out.println("Server started. Awaiting connection...");
            Socket sock = serverSock.accept();
            System.out.println("Connected!");

            Scanner scnr = new Scanner(System.in);

            RetrieveMessage test = new RetrieveMessage(sock.getInputStream());
            Thread thread = new Thread(test);
            thread.setDaemon(true);
            thread.start();

            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            String message = scnr.nextLine();
            while( !message.equalsIgnoreCase("end")){
                out.writeUTF(message);
                message = scnr.nextLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.toString());
        }




    }
}
