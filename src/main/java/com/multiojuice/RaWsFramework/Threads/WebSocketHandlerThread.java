package com.multiojuice.RaWsFramework.Threads;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;

public class WebSocketHandlerThread extends Thread {
    private Socket socket;
    private Resolver protocolResolver;
    private HashMap<String, String> headers;
    private boolean isConnected;
    private BufferedReader reader;
    public WebSocketHandlerThread(Socket socket, Resolver protocolResolver, HashMap<String, String> headers) {
        this.socket = socket;
        this.protocolResolver = protocolResolver;
        this.headers = headers;
        try {
            InputStreamReader isr = new InputStreamReader(this.socket.getInputStream());
            this.reader = new BufferedReader(isr);
        } catch (Exception e) {
            System.out.println(e);
        }
        this.isConnected = true;
    }

    @Override
    public void run() {
        if (headers.containsKey("Sec-WebSocket-Key")) {
            returnHandshake();
            while (isConnected) {

                System.out.println("got a WebSocket request");
            }
        } else {
            System.out.println("Return something that says its not a websocket");
        }
    }

    private void returnHandshake() {
        String acceptKey = getAcceptFromKey(headers.get("Sec-WebSocket-Key"));

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

        return acceptKey;
    }
}