package com.multiojuice.RaWsFramework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class RaWsApp extends Thread {
    private ServerSocket server;
    private HashMap<String, Resolver>  endpoints;

    public RaWsApp() {
        endpoints = new HashMap<>();
        try {
            server = new ServerSocket(8080);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public HashMap<String, Resolver> getEndpoints() {
        return endpoints;
    }

    public void run() {
        while(true) {
            try (Socket socket = server.accept()) {
                InputStreamReader isr =  new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);

                String line = reader.readLine();
                String[] splitLine = line.split("\\s+");

                Resolver neededResolver = endpoints.get(splitLine[1]);
                CallResolver callResolver = new CallResolver(neededResolver, RequestType.POST);
                callResolver.start();

                System.out.println("Got a request");
            } catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}
