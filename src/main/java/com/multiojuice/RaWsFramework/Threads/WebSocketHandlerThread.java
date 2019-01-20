package com.multiojuice.RaWsFramework.Threads;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

public class WebSocketHandlerThread extends Thread {
    private ServerSocket server;
    private HashMap<String, Resolver> protocols;

    public WebSocketHandlerThread(HashMap<String, Resolver> newProtocols) {
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