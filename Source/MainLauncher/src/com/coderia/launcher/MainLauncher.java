/* MainLauncher.java
 * ---
 * Author: Danial Fitri (dfx)
 * This is the entry point of the program
 * Create the connection to the Server, create the Launcher UI, and
 * display the UI.
 */

package com.coderia.launcher;

// Imports
import com.coderia.airlines.AirlineContent;
import airport.Airport;
import com.coderia.backend.Client;
import com.coderia.frontend.Launcher;
import com.coderia.frontend.Content;

// Start of class
public class MainLauncher {
    // Main method
    public static void main(String[] args) {
        // For now, don't connect to anything (server is offline :-P)
        // Feel free to connect to localhost if you want
        
        Client client = new Client("127.0.0.1", 8080);
        
        // List all of the Contents. For now, all of it are CreditContents
        Content[] topics = {
            new Airport(),
            new AirlineContent(),
            new CreditContent(),
            new CreditContent(),
            new CreditContent(),
            new CreditContent()
        };
        
        // The title of the topics to be displayed on the buttons
        String[] titles = {
            "Airport",
            "Airlines",
            "Airplane",
            "Restaurant",
            "Hotel",
            "Credits"
        };
        
        // Start the launcher
        Launcher ui = new Launcher(topics, titles, client);
        ui.buildUI();
    }
}


