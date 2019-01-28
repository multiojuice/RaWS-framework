package com.multiojuice.RaWsFramework.Controllers;

import com.multiojuice.RaWsFramework.RequestType;
import com.multiojuice.RaWsFramework.Resolvers.Resolver;
import com.multiojuice.RaWsFramework.Threads.HTTPHandlerThread;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HTTPController implements Runnable{

    private ServerSocket server;
    private HashMap<String, Resolver> endpoints;

    public HTTPController(HashMap<String, Resolver> newEndpoints) {
        endpoints = newEndpoints;
        try {
            server = new ServerSocket(8080);
        } catch(Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void run() {
        while (true) {
            try (Socket socket = server.accept()) {
                HTTPHandlerThread httpHandlerThread = new HTTPHandlerThread(endpoints, socket);
                Thread thread = new Thread(httpHandlerThread);
                thread.start();
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

    private RequestType getRequestTypeFromString(String requestTypeString) {
        System.out.println(requestTypeString);
        switch (requestTypeString.toUpperCase()) {
            case "GET":
                return RequestType.GET;
            case "POST":
                return RequestType.POST;
            case "PUT":
                return RequestType.PUT;
            case "PATCH":
                return RequestType.PATCH;
            case "DELETE":
                return RequestType.DELETE;
            default:
                System.out.println("Incorrect http request type");
                return null;
        }
    }
}
