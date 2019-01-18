package com.multiojuice.RaWsFramework;

import java.net.ServerSocket;
import java.net.Socket;

public class RaWsApp extends Thread {
    private ServerSocket server;

    public RaWsApp() {
        try {
            server = new ServerSocket(8080);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void run() {
        while(true) {
            try (Socket socket = server.accept()) {
                System.out.println("Got a request");
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
