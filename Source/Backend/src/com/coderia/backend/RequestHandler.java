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
package com.coderia.backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class RequestHandler {

    private PrintWriter out;
    private BufferedReader in;
    private ArrayList<String> extras = new ArrayList<>();

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
            case "/airlines-w":
                airlinesWrite();
                break;
            case "/airlines-r":
                airlinesRead();
                break;
            default:
                extras.add(req);
        }
    }

    private void airlinesWrite() {
        File airlines = new File("./res/data.txt");

        try {
            PrintWriter pw = new PrintWriter(airlines);
            pw.write("");
            for (String str : extras) {
                pw.println(str);
            }
            
            extras.clear();
            pw.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void airlinesRead() {
        File airlines = new File("./res/data.txt");

        try {
            Scanner sc = new Scanner(airlines);

            while (sc.hasNext()) {
                int type = 0;
                out.println(sc.nextLine());
                out.println(sc.nextInt());
                type = sc.nextInt();
                out.println(type);
                out.println(sc.nextInt());
                out.println(sc.nextInt());
                out.println(sc.nextDouble());
                out.println(sc.nextBoolean());
                
                if (type == 1) {
                    out.println(sc.nextInt());
                    int n = sc.nextInt();
                    out.println(n);
                    for (int i = 0; i != n; i++)
                        out.println(sc.nextInt());
                }
                
                sc.nextLine();
            }
            
            out.println("/eof");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
