package com.coderia.launcher;

// Imports
import com.coderia.airlines.AirlineContent;
import airport.Airport;
import com.coderia.airplane.newSop;
import com.coderia.backend.Client;
import com.coderia.frontend.Launcher;
import com.coderia.frontend.Content;
import com.coderia.Restaurant.GroupProjectSOP;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JOptionPane;

// Start of class
public class MainLauncher {
    // Main method
    public static void main(String[] args) {
        // For now, don't connect to anything (server is offline :-P)
        // Feel free to connect to localhost if you want
        
        //Client client = new Client("127.0.0.1", 8080);
        
        // List all of the Contents. For now, all of it are CreditContents
        Content[] topics = {
            new Airport(),
            new AirlineContent(),
            new newSop(),
            new GroupProjectSOP(),
            null,
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
        
        
        File config = new File("config.txt");
        
        try {
            Scanner sc = new Scanner(config);
            String ip = sc.nextLine();
            int port = sc.nextInt();
            sc.close();
            
            Client client = new Client(ip, port);
            if (client.connect() == -1)
                throw new IOException("PC or Server is Offline, retry later");
            
            // Start the launcher
            Launcher ui = new Launcher(topics, titles, client);
            ui.buildUI();
        } catch (FileNotFoundException err) {
            JOptionPane.showMessageDialog(null, "Configuration can't be read : "
            + err);
            System.exit(-1);
        } catch (IOException err) {
            JOptionPane.showMessageDialog(null, err);
            System.exit(-1);
        }
    }
}