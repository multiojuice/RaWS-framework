package com.multiojuice.RaWsFramework.Controllers;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;

public class WebSocketController extends Thread {
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
                getAcceptFromKey("dGhlIHNhbXBsZSBub25jZQ==");

                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
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

    private String getAcceptFromKey(String key) {
        String concatKey = key + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
        String acceptKey = null;
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(concatKey.getBytes());
            acceptKey = Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(acceptKey);
        return acceptKey;
    }
}