/* Server.java (backend.Server)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : Simply import from package backend
 * A class used to handle multiple connections
 * and create new ServerInstances to handle
 * them in a separate thread.
 * Provide the port the server will listen to.
 * Then the Server will begin listening for
 * connections in a separate thread.
 * In the main thread, the server can be
 * controlled by typing commands such as
 * /exit which shuts the server down.
 */

package backend;

// Imports
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;

// Implements the Runnable interface to
// perform multithreaded tasks.
public class Server implements Runnable {
    // Use ServerSocket to listen for
    // connections.
    // Then create new Sockets for each
    // connections.
    private boolean running = true;
    private ServerSocket server;
    private Socket socket;
    
    // Constructor. Pass the port number to
    // listen to.
    // Then start listening in separate thread.
    // In current thread, perform control
    // operations.
    public Server(int port) {
        try {
            server = new ServerSocket(port);
        } catch (Exception err) {
            //err.printStackTrace();
        }
        
        new Thread(this).start();
        control();
    }
    
    // Control the server. It will listen
    // to commands. Used switch statements
    // so more commands can be added.
    public void control() {
        System.out.println("Running...");
        
        Scanner in = new Scanner(System.in);
        String res = "";
        
        while (running) {
            res = in.nextLine();
            System.out.println("> " + res);
            
            switch (res) {
                case "/exit":
                    running = false;
                    System.out.println("Running: " + running);
                    break;
                default:
                    break;
            }
        }
        
        try {
            System.out.println("Shutting down server...");
            server.close();
        } catch (Exception err) {
            
        }
        
        in.close();
    }
    
    // MANDATORY OVERRIDE (from Runnable)
    @Override
    public void run() {
        // Keep listening for new connections
        // while the server is running.
        // If new connection was found, create
        // a new Socket and pass it as args
        // for new ServerInstance object.
        while (running) {
            try {
                socket = server.accept();
                new ServerInstance(socket);
            } catch (Exception err) {
                //err.printStackTrace();
            }
        }
    }
}