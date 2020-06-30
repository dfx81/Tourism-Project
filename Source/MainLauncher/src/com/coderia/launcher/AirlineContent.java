package com.coderia.launcher;

import com.coderia.backend.Client;
import com.coderia.frontend.Content;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class AirlineContent extends Content  {
    @Override
    public void buildUI(Client client) {
        JLabel credits = new JLabel("Hello World");
        
        // Set the Content layout to Borderlayout
        this.setLayout(new BorderLayout());
        
        // Set the horizontalAlignment of the JLabel
        credits.setHorizontalAlignment(JLabel.CENTER);
        
        // Add the JLabel to the Content
        this.add(credits, BorderLayout.CENTER);
    }
}
