import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BookClient
{

    public static void main(String[] args) throws IOException
    {
        if (args.length != 2) {
            System.err.println("Pass the server IP (should be localhost) as the first argument, and the port for the server as the second (should be 54321)");
            return;
        }

        int port = Integer.parseInt(args[1]);
        String ipAddress = args[0];

        //make a socket via localhost and give it a port number
        Socket socket = new Socket(ipAddress, port);

        //tell user on this side where it's connected to
        System.out.println("Connected to " + socket.getRemoteSocketAddress());
        System.out.println("Enter SEARCH <bookName> or ADD <ISBN> <author> <publisher> <title> <language> <price>");

        //set up a reader and writer connected to the server side, and scanner in for client user input

        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            Scanner receivingIn = new Scanner(socket.getInputStream());
            //send out the message the user at this end has input
            String userEnteredLine = scanner.nextLine();
            outToServer.println(userEnteredLine);
            System.out.println(receivingIn.nextLine());
        }
    }
}