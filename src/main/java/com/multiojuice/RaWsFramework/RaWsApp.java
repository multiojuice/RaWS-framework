package com.multiojuice.RaWsFramework;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;
import com.multiojuice.RaWsFramework.Threads.HTTPHandlerThread;
import com.multiojuice.RaWsFramework.Threads.WebSocketHandlerThread;

import java.util.HashMap;

public class RaWsApp extends Thread {
    private HashMap<String, Resolver> httpEndpoints;
    private HTTPHandlerThread httpHandlerThread;

    private HashMap<String, Resolver> webSocketProtocols;
    private WebSocketHandlerThread webSocketHandlerThread;

    public RaWsApp(HashMap<String, Resolver> newHTTPEndpoints, HashMap<String, Resolver> newWebSocketProtocols) {
            httpEndpoints = newHTTPEndpoints;
            webSocketProtocols = newWebSocketProtocols;
    }

    public void run() {
        if(httpEndpoints != null) {
            httpHandlerThread = new HTTPHandlerThread(httpEndpoints);
            httpHandlerThread.start();
        }

        if(webSocketProtocols != null) {
           webSocketHandlerThread = new WebSocketHandlerThread(webSocketProtocols);
           webSocketHandlerThread.start();
        }
    }

}
