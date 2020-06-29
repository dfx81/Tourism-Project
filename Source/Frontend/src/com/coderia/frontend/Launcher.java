/* Launcher.java (com.coderia.frontend.Launcher)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : Simply import, instantiate and call buildUI()
 * A class used as the main UI of the program.
 * Based on Android's Fragment system.
 * There's two areas of UI:
 * - The navigation buttons
 * - The content area
 * The navigation buttons will trigger specific Content panes to display
 * in the content area. Content shown are subclasses of Content class.
 * This is similar to Android's Fragment system (kinda)
 */

package com.coderia.frontend;

// Imports
import com.coderia.backend.Client;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;

// Launcher class: it will have access to the backend through Client class
public class Launcher{
    private final Client client;
    private final JFrame frame;
    private final JPanel nav;
    private final JScrollPane content;
    private final JButton[] buttons;
    private JLabel text;
    
    private final String[] titles;

    private final Content[] topics;
    
    // Constructor
    // Pass in the Content array, name of the topics for the contents, and
    // the Client class to connect to Server
    public Launcher(Content[] topics, String[] titles, Client client) {
        this.client = client;
        frame = new JFrame("untitled tourism project");
        nav = new JPanel();
        content = new JScrollPane();
        buttons = new JButton[6];
        this.topics = topics;
        this.titles = titles;
    }

    // buildUI() used to initialize and display the main UI
    public final void buildUI() {
        // Setup the frame
        frame.setLayout(new BorderLayout());
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Setting up the navigation area
        // Nav are added to the west of BorderLayout
        Dimension navD = new Dimension(160, 480);
        nav.setSize(navD);
        nav.setPreferredSize(navD);
        nav.setMinimumSize(navD);
        nav.setMaximumSize(navD);
        frame.add(nav, BorderLayout.WEST);
        
        // Setting up the content area
        // content are added to the center of Borderlayout
        // Content area are created using JScrollPane, so it can handle
        // any kind of content sizes.
        // Also, scroll speed set to 16
        Dimension ctnD = new Dimension(480, 480);
        content.setSize(ctnD);
        content.setPreferredSize(ctnD);
        content.setMinimumSize(ctnD);
        content.setMaximumSize(ctnD);
        content.getVerticalScrollBar().setUnitIncrement(16);
        frame.add(content, BorderLayout.CENTER);
        
        // Get all of the Content list and start initializing each of
        // their UIs
        for (Content topic : topics) {
            topic.buildUI(client);
        }
        
        // Set default content to the credit section :-P
        //content.setViewportView(topics[5]);
        content.setViewportView(topics[5]);
        
        // - Navigation -
        
        // Set the text above the nav area (for decoration)
        Dimension textD = new Dimension(160, 20);
        text = new JLabel("Placeholder text");
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setSize(textD);
        text.setPreferredSize(textD);
        text.setMinimumSize(textD);
        text.setMaximumSize(textD);
        nav.add(text);
        
        // Setup the navigation buttons.
        // All buttons correspond to a Content and displays the topic title
        Dimension navElmD = new Dimension(160, 40);
        for (int i = 0; i != buttons.length; i++) {
            buttons[i] = new JButton(titles[i]);
            buttons[i].setSize(navElmD);
            buttons[i].setPreferredSize(navElmD);
            buttons[i].setMinimumSize(navElmD);
            buttons[i].setMaximumSize(navElmD);
            
            nav.add(buttons[i]);
        }
        
        // Detects click events using Lambda functions
        // All it does when a click is detected is to change the
        // Content to the correct Content.
        setActions();
        
        // Display the ui
        frame.setVisible(true);
    }
    
    private void setActions() {
        buttons[0].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[0]);
        });
        buttons[1].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[1]);
        });
        buttons[2].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[2]);
        });
        buttons[3].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[3]);
        });
        buttons[4].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[4]);
        });
        buttons[5].addActionListener((ActionEvent evt) -> {
            content.setViewportView(topics[5]);
        });
    }
}
