/* Client.java (backend.Client)
 * ---
 * Author: Danial Fitri (dfx)
 * Usage : Simply import from package backend
 * A class used to send requests and receive
 * responses from a server.
 * Provide ip address of server and the port
 * the server listen to.
 * Then perform requests using
 * sendRequest(String message) and receive
 * response using getResponse().
 */

package com.coderia.backend;

// Imports
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {
    // Socket used to connect to the server
    // Printwriter for sending requests
    // Buffered for listening for response
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    
    // Basic constructor. If this constructor
    // was used, must call
    // connect(String ip, int port)
    public Client() {
        // Do nothing
    }
    
    // It's better to use this constructor.
    // It automatically call connect() to
    // establish connection immediately
    public Client(String ip, int port) {
        connect(ip, port);
    }
    
    // Use socket to connect to the server.
    // Note however the server must be running
    // or it will fail.
    public void connect(String ip, int port) {
        // Immediately get the InputStream and
        // OutputStream from the connection
        // to start sending requests and 
        // receiving responses.
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception err) {
            // Todo: Handle exception properly
        }
    }
    
    // Call this to close the connection
    // with the server.
    public void closeConnection() {
        try {
            sendRequest("/exit");
            in.close();
            out.close();
            socket.close();
        } catch (Exception err) {
            // Todo: Handle exception properly
        }
    }
    
    // Use this method to send request to the
    // server. Depending on the request, the
    // server might or might not handle it.
    public void sendRequest(String message) {
        try {
            out.println(message);
        } catch (Exception err) {
            // Todo: Handle exception properly
        }
    }
    
    // When request was sent and a response
    // will be sent from the server, use this
    // method to get the response as String.
    public String getResponse() {
        String response = "";
        
        try {
            response = in.readLine();
        } catch (Exception err) {
            // Todo: Handle exception properly
        }
        
        return response;
    }
}