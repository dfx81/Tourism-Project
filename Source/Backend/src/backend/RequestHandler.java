/* RequestHandler.java (backend.RequestHandler)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : Simply import from package backend
 * A class used to handle requests.
 * Not to be called manually, instead will be
 * called by a Worker instance.
 * Edit this class to suit whatever the project
 * needs.
 */

package backend;

import java.io.BufferedReader;
import java.io.PrintWriter;

class RequestHandler {
    private PrintWriter out;
    private BufferedReader in;
    
    // Constructor
    // Get the inputstream and outputstream for responses.
    // Use the inputstream to read further input such as extra
    // additional data, and use the outputstream to send responses
    // back to the Client
    public RequestHandler(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
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
    public void respond(String req) {
        switch (req) {
            case "/rock":
                game(1);
                break;
            case "/paper":
                game(2);
                break;
            case "/scissor":
                game(3);
                break;
            default:
                out.println("Try typing /rock, /paper, or /scissor");
        }
    }
    
    // Just a game of rock paper scissor.
    // Nothing to see here...
    private void game(int user) {
        int bot = 1 + (int)(Math.random() * 3);
        int result = 0;
        
        switch (user) {
            case 1:
                switch (bot) {
                    case 1:
                        result = 0;
                        break;
                    case 2:
                        result = -1;
                        break;
                    case 3:
                        result = 1;
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                switch (bot) {
                    case 1:
                        result = 1;
                        break;
                    case 2:
                        result = 0;
                        break;
                    case 3:
                        result = -1;
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                switch (bot) {
                    case 1:
                        result = -1;
                        break;
                    case 2:
                        result = 1;
                        break;
                    case 3:
                        result = 0;
                        break;
                    default:
                        break;
                }
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
    }
}
