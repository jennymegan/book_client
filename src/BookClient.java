import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BookClient {

    public static void main(String[] args) throws IOException {
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

        //give user their options
        System.out.println("***** Welcome to the Bookstore *****");
        System.out.println("Enter BOOK SEARCH <bookName>");
        System.out.println("Enter BOOK ADD <ISBN> <author> <publisher> <title> <language> <priceInGBP like 6.99>");
        System.out.println("Enter BOOK UPDATE <ISBN> <field_to_update> <updated_data> (available field names are title and price)");
        System.out.println("Enter BOOK DELETE <ISBN>");
        System.out.println("Enter CUSTOMER SEARCH <Name>");
        //set up a reader and writer connected to the server side, and scanner in for client user input
        Scanner scanner = new Scanner(System.in);
        PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

        while (scanner.hasNext()) {
            Scanner receivingIn = new Scanner(socket.getInputStream());
            //send out the message the user at this end has input
            String dbQuery = scanner.nextLine();
            outToServer.println(dbQuery);
            System.out.println(receivingIn.nextLine());
        }
    }



}