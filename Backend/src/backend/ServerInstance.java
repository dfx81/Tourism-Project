/* ServerInstance.java (backend.ServerInstance)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : Simply import from package backend
 * A class used to handle requests and send
 * responses to Clients.
 * It will continuously listen for new requests
 * and will handle them if possible.
 * The respond method will be called that will
 * respond to the requests appropriately.
 * This class will be continually updated to
 * reflect what the team needs from the server
 * in the program.
 */

package backend;

// Imports
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// Implements Runnable to do multithreading
// tasks.
class ServerInstance implements Runnable {
    // The Socket will be connected to a
    // single Client
    // Use PrintWriter to send responses and
    // BufferedReader to get requests.
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    // Constructor. Pass in the connected
    // Socket. Get the required DataStream
    // and start a new Thread to be able
    // to handle multiple connections at
    // the same time. Basically, a thread will
    // be created for each Client connected.
    public ServerInstance(Socket socket) {
        this.socket = socket;
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception err) {
            
        }
        
        new Thread(this).start();
        System.out.println("Connected");
    }
    
    // MANDATORY OVERRIDE (from Runnable)
    @Override
    public void run() {
        boolean running = true;
        String request = "";
        
        // Continuously listening to requests.
        // Then call respond method.
        // Will stop if the request is /exit
        while (running) {
            try {
                request = in.readLine();
                System.out.println("Request: " + request);
                
                if ((request == null) || request.equals("/exit")) {
                    System.out.println("Closing Connection");
                    running = false;
                } else {
                    respond(request);
                }
            } catch (Exception err) {
                
            }
        }
        
        // Close the socket because no longer
        // needed
        try {
            socket.close();
        } catch (Exception err) {
            
        }
        
        return;
    }
    
    // This method defines what to do for
    // each requests.
    // This method will be changed repeatedly
    // during the course of the project
    // development to reflect the needs of the
    // project.
    // For now, it just responds to a game of
    // rock paper scissor and call the game
    // method.
    private void respond(String req) {
        System.out.println("Handling response");
        
        try {
            int result = 0;
            switch (req) {
                case "/rock":
                    result = game(1);
                    break;
                case "/paper":
                    result = game(2);
                    break;
                case "/scissor":
                    result = game(3);
                    break;
                default:
                    out.println("Try typing /rock, /paper, or /scissor");
            }
            
            switch (result) {
                case -1:
                    out.println("You lost");
                    break;
                case 0:
                    out.println("Tied");
                    break;
                case 1:
                    out.println("You won");
            }
            
            System.out.println("Response sent");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
    
    // Just a game of rock paper scissor.
    // Nothing to see here...
    private int game(int user) {
        int bot = 1 + (int)(Math.random() * 3);
        int result = 0;
        
        switch (user) {
            case 1:
                if (bot == 1) result = 0;
                else if (bot == 2) result = -1;
                else if (bot == 3) result = 1;
                break;
            case 2:
                if (bot == 1) result = 1;
                else if (bot == 2) result = 0;
                else if (bot == 3) result = -1;
                break;
            case 3:
                if (bot == 1) result = -1;
                else if (bot == 2) result = 1;
                else if (bot == 3) result = 0;
        }
        
        return result;
    }
}