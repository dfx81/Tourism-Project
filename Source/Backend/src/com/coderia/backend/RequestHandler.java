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
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            case "/airport-sop-r":
                airportRead(0);
                break;
            case "/airport-msop-r":
                airportRead(1);
                break;
            case "/airport-msop-w":
                airportWrite(1);
                break;
            case "/airport-isop-r":
                airportRead(2);
                break;
            case "/airport-isop-w":
                airportWrite(2);
                break;
            case "/airplane-w":
                airplaneWrite();
                break;
            case "/airplane-r":
                airplaneRead();
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

            extras.forEach((str) -> pw.println(str)); // Lambda :-P

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
                    for (int i = 0; i != n; i++) {
                        out.println(sc.nextInt());
                    }
                }

                sc.nextLine();
            }

            out.println("/eof");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void airportRead(int target) {
        File airport;

        try {
            switch (target) {
                case 0:
                    airport = new File("./res/GeneralSOP.txt");
                    break;
                case 1:
                    airport = new File("./res/SopMalaysia.txt");
                    break;
                case 2:
                    airport = new File("./res/SopIndia.txt");
                    break;
                default:
                    throw new Exception("File not found");
            }

            Scanner sc = new Scanner(airport);

            while (sc.hasNext()) {
                out.println(sc.nextLine());
            }

            out.println("/eof");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void airportWrite(int target) {
        File airport;

        try {
            switch (target) {
                case 1:
                    airport = new File("./res/SopMalaysia.txt");
                    break;
                case 2:
                    airport = new File("./res/SopIndia.txt");
                    break;
                default:
                    throw new Exception("File not found");
            }

            PrintWriter pw = new PrintWriter(airport);

            pw.write("");

            extras.forEach((str) -> pw.println(str)); // lambda :-P

            extras.clear();
            pw.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void airplaneWrite() {
        File airplane = new File("./res/passengerInfo.txt");

        try {

            RandomAccessFile raf = new RandomAccessFile(airplane, "rw");

            extras.forEach((str) -> {
                try {
                    raf.writeBytes(str + System.lineSeparator());
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }); // lambda :-P

            extras.clear();
            raf.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void airplaneRead() {
        File airplane = new File("./res/passengerInfo.txt");

        try {

            Scanner sc = new Scanner(airplane);

            while (sc.hasNext()) {
                out.println(sc.nextLine());
            }

            out.println("/eof");
        } catch (Exception err) {
            err.printStackTrace();
        }
    }
}
