/* Content.java (com.coderia.frontend.Content)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : The Content class provides the skeleton of a basic
 * content pane that will be used in the the full launcher.
 * Since it's an abstract class, you must extend the class with
 * another class instead. The buildUI() method must also be
 * overridden.
*/

package com.coderia.frontend;

// The Content will have access to the Client class from
// com.coderia.backend.Client package.
import com.coderia.backend.Client;
import javax.swing.JPanel;

// The Content class is a subclass of JPanel, making it easier to
// add other Swing components to it and makes it easier to pass
// the created UI layout around.
public abstract class Content extends JPanel {
    // Abstract method buildUI must be overridden (there's no method body,
    // so you can build your own UI layout inside the buildUI method.
    // It will also have access to the server through the Client class).
    public abstract void buildUI(Client client);
    
    // For testing (no longer needed)
    /*public final JPanel getContent() {
        return this;
    }*/
}