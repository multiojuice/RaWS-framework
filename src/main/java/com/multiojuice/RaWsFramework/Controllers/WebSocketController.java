package com.multiojuice.RaWsFramework.Controllers;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;
import com.multiojuice.RaWsFramework.Threads.WebSocketHandlerThread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;

public class WebSocketController implements Runnable {
    private ServerSocket server;
    private HashMap<String, Resolver> protocols;


    public WebSocketController(HashMap<String, Resolver> newProtocols) {
        protocols = newProtocols;
        try {
            server = new ServerSocket(1337);
        } catch(Exception e) {
            System.out.println("Error creating web-socket server");
        }
    }


    @Override
    public void run() {
        while (true) {
            try (Socket socket = server.accept()) {
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);

                String firstLine = reader.readLine();
                HashMap<String, String> headers = getHeadersFromBR(reader);

                if (headers.containsKey("Sec-WebSocket-Protocol")) {
                    WebSocketHandlerThread webSocketHandlerThread = new WebSocketHandlerThread(socket, protocols.get("Sec-WebSocket-Protocol"), headers);
                    webSocketHandlerThread.start();
                }
                System.out.println("got a WebSocket request");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    private HashMap<String, String> getHeadersFromBR(BufferedReader reader) {
        HashMap<String, String> headers = new HashMap<>();

        try {
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
                String[] splitLine = line.split(": ");
                headers.put(splitLine[0], splitLine[1]);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return headers;
    }
}