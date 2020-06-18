/* Worker.java (backend.Worker)
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
class Worker implements Runnable {
    // The Socket will be connected to a
    // single Client
    // Use PrintWriter to send responses and
    // BufferedReader to get requests.
    // RequestHandler will handle all requests
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private RequestHandler handler;
    
    // Constructor. Pass in the connected
    // Socket. Get the required DataStream
    // and start a new Thread to be able
    // to handle multiple connections at
    // the same time. Basically, a thread will
    // be created for each Client connected.
    public Worker(Socket socket) {
        this.socket = socket;
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            handler = new RequestHandler(in, out);
        } catch (Exception err) {
            // Todo: Handle exceptions properly
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
                // Todo: catch exception properly
            }
        }
        
        // Close the socket because no longer
        // needed
        try {
            socket.close();
        } catch (Exception err) {
            // Todo: Handle exceptions properly
        }
        
        return;
    }
    
    
    private void respond(String req) {
        System.out.println("Handling response");
        handler.respond(req);
        System.out.println("Response sent");
    }
}