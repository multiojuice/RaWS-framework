package com.multiojuice.RaWsFramework.Threads;

import com.multiojuice.RaWsFramework.RequestType;
import com.multiojuice.RaWsFramework.Resolvers.CallResolver;
import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class HTTPHandlerThread extends Thread{

    private ServerSocket server;
    private HashMap<String, Resolver> endpoints;

    public HTTPHandlerThread(HashMap<String, Resolver> newEndpoints) {
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
                InputStreamReader isr = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(isr);

                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                String[] splitLine = line.split("\\s+");
                RequestType currentRequestType = getRequestTypeFromString(splitLine[0]);

                Resolver neededResolver = endpoints.get(splitLine[1]);
                System.out.println(endpoints);
                CallResolver callResolver = new CallResolver(neededResolver, currentRequestType);
                callResolver.start();

                System.out.println("Got a request");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
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
