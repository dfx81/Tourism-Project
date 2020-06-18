/* Class to test the Client
 * Run this class to start a Client.
 * It will connect to localhost on port 8080
 * as a default.
 */

import backend.Client;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 8080);
        Scanner in = new Scanner(System.in);
        
        String req = "";
        boolean running = true;
        
        while (running) {
            req = in.nextLine();
            client.sendRequest(req);
            
            if (req.equals("/exit")) {
                client.closeConnection();
                running = false;
            } else {
                System.out.println(client.getResponse());
            }
        }
        
        in.close();
    }
}