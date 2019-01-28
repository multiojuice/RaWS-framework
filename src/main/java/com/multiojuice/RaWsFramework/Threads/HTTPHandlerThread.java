package com.multiojuice.RaWsFramework.Threads;

import com.multiojuice.RaWsFramework.RequestType;
import com.multiojuice.RaWsFramework.Resolvers.CallResolver;
import com.multiojuice.RaWsFramework.Resolvers.HTTPMethodsResolver;
import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class HTTPHandlerThread implements Runnable{

    private Socket socket;
    private HashMap<String, Resolver> endpoints;
    private InputStreamReader isr;
    private BufferedReader reader;
    private PrintWriter out;
    private  BufferedOutputStream dataOut;


    public HTTPHandlerThread(HashMap<String, Resolver> endpoints, Socket socket) {
        this.endpoints = endpoints;
        this.socket = socket;

        try {
            this.isr = new InputStreamReader(socket.getInputStream());
            this.reader = new BufferedReader(isr);

            this.out = new PrintWriter(socket.getOutputStream());
            this.dataOut = new BufferedOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void run() {
        while (true) {
            try {
                String line = reader.readLine();
                String[] splitLine = line.split("\\s+");
                RequestType currentRequestType = getRequestTypeFromString(splitLine[0]);

                getHeadersFromBR(reader);
                Resolver neededResolver = endpoints.get(splitLine[1]);

                HTTPMethodsResolver httpMethodsResolver = (HTTPMethodsResolver) neededResolver;
                httpMethodsResolver.setPrintWriter(out);
                httpMethodsResolver.setRequestType(currentRequestType);

                System.out.println(endpoints);

                CallResolver callResolver = new CallResolver(httpMethodsResolver);
                Thread thread = new Thread(callResolver);
                System.out.println("Got a request");
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

