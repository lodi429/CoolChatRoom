import java.io.*;
import java.net.Socket;

public class RetrieveMessage extends Thread{

    Socket sock;
    DataInputStream in;

    public RetrieveMessage(InputStream in) throws IOException {
        this.in = new DataInputStream(in);
    }

    @Override
    public void run() {
        while(true){
            try {
                String response = in.readUTF();
                System.out.println("Message: " + response);
            }catch( EOFException e){
                System.exit(0);
            } catch (IOException e) {
                System.out.println("ERROR: " + e.toString());
            }
        }
    }
}
