/* CreditContent.java
 * ---
 * This is an example on how to use the Content class.
 * This example will use the Content class to build a simple Swing based
 * credits UI.
 */

package com.coderia.launcher;

// Import the required classes
import com.coderia.backend.Client;
import com.coderia.frontend.Content;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;

// Make sure you extends Content
public class CreditContent extends Content {
    // Override the buildUI method. It accepts a Client as args and returns
    // void. For the body, use it to implement your own UI.
    // If you want to add a component such as a Button, just add it to the class
    // itself.
    @Override
    public void buildUI(Client client) {
        // Create a JLabel and display the credits using html formating
        JLabel credits = new JLabel("<html>"
                + "<h1 style='text-align:center'>The Tour Guide</h1><br/>"
                + "<p style='text-align:center'>"
                + "Created by Coders in Action (coderia)<br/><br/>"
                + "- Programmers - <br/>"
                + "Danial Fitri<br/>"
                + "Afirudin<br/>"
                + "Gokul<br/>"
                + "Galvin<br/>"
                + "Jason<br/><br/>"
                + "- Backend Programming -<br/>"
                + "Danial Fitri<br/><br/>"
                + "- Frontend Programming -<br/>"
                + "Danial Fitri</p></html>");
        
        // Set the Content layout to Borderlayout
        this.setLayout(new BorderLayout());
        
        Dimension d = new Dimension(480 - 24, 480 - 24);
        
        credits.setSize(d);
        credits.setPreferredSize(d);
        credits.setMaximumSize(d);
        credits.setMinimumSize(d);
        
        // Set the horizontalAlignment of the JLabel
        credits.setHorizontalAlignment(JLabel.CENTER);
        
        // Add the JLabel to the Content
        this.add(credits, BorderLayout.CENTER);
    }
}
