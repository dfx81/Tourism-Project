/* Class to test the server
 * Run this class to start the server.
 * It will listen to port 8080 on localhost
 * as a default.
 */

import backend.Server;

class Main {
    public static void main(String[] args) {
        new Server(8080);
    }
}