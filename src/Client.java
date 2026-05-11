import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static void main() {
        System.out.println("Connecting...");
        try (Socket sock = new Socket("127.0.0.1", 9999);) {
            System.out.println("Connected!");
            DataInputStream in = new DataInputStream(sock.getInputStream());
            DataOutputStream out = new DataOutputStream(sock.getOutputStream());

            RetrieveMessage test = new RetrieveMessage(sock.getInputStream());
            Thread thread = new Thread(test);
            thread.setDaemon(true);
            thread.start();


            Scanner scnr = new Scanner(System.in);

            String message = scnr.nextLine();
            while( !message.equalsIgnoreCase("end")){
                out.writeUTF(message);
                message = scnr.nextLine();
            }

        }catch (IOException e) {
            System.out.print("ERROR: " + e.toString());
        }
    }
}