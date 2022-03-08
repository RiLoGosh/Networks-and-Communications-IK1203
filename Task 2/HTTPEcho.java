import java.net.*;
import java.io.*;


public class HTTPEcho {

    public static void main(String[] args) throws IOException {

        int port = Integer.parseInt(args[0]);
        ServerSocket serversock = new ServerSocket(port);
        System.out.println("Started server on port " + port);

        while (1 == 1){

            // Create client socket
            Socket clientsocket = serversock.accept();
            System.out.println("Connection to client: Successful");
            clientsocket.setSoTimeout(30000);                      // Will timeout after 30 seconds of listening


            // Create I/O Streams
            InputStreamReader ISR = new InputStreamReader(clientsocket.getInputStream());
            BufferedReader inFromClient = new BufferedReader(ISR);
            DataOutputStream OutToClient = new DataOutputStream(clientsocket.getOutputStream());

            // Create Strings

            String s = null;
            StringBuilder CompleteString = new StringBuilder();


            while (!(s = inFromClient.readLine()).isEmpty()){

                CompleteString.append(s + "\n");

            }

            System.out.println("Client Message: ");
            System.err.println(CompleteString);
            // Send back the information to client
            OutToClient.writeBytes("HTTP/1.1 200 OK\r\n\r\n");
            OutToClient.writeBytes(CompleteString.toString());

            // Close all connections
            inFromClient.close();
            OutToClient.close();
            serversock.close();
            clientsocket.close();
            System.out.println("Connection closed");

        }
    }

}
