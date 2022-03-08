package tcpclient;
import java.net.*;
import java.io.*;

public class TCPClient {

    // For connecting to a server with hostname, port number and an additional parameter (ToServer)
    public static String askServer(String hostname, int port, String ToServer) throws  IOException {

        if (ToServer == null){
            return askServer(hostname, port);               // If there is no additional parameter, then other askServer method is called
        }

        Socket clientsock = new Socket(hostname, port);
        clientsock.setSoTimeout(5000);                      // Will timeout after 5 seconds of listening


        DataOutputStream OutToServer = new DataOutputStream(clientsock.getOutputStream());
        InputStreamReader ISR = new InputStreamReader(clientsock.getInputStream());
        BufferedReader inFromServer = new BufferedReader(ISR);

        OutToServer.writeBytes(ToServer + "\n");

        StringBuilder BigString = new StringBuilder();
        String s = "";

        //Accidentally sent this assignment in with the following debug statement still in place
        //System.out.println(s);

        try {
            while ((s = inFromServer.readLine()) != null) {
                BigString.append(s + "\n");
            }

            clientsock.close();
            return BigString.toString();
        }
        catch (IOException e) {
            clientsock.close();
            return BigString.toString();
        }
    }

    // For connecting to a server with only a hostname and port number
    public static String askServer(String hostname, int port) throws  IOException {

        StringBuilder BigString = new StringBuilder();
        String s = "";

        Socket clientsock = new Socket(hostname, port);

        clientsock.setSoTimeout(5000);                      // Will timeout after 5 seconds of listening

        InputStreamReader ISR = new InputStreamReader(clientsock.getInputStream());
        BufferedReader inFromServer = new BufferedReader(ISR);
        try {
            while ((s = inFromServer.readLine()) != null) {
                BigString.append(s + "\n");
            }
            clientsock.close();
            return BigString.toString();
        }
        catch (IOException e) {
            clientsock.close();
            return BigString.toString();
        }
    }
}

