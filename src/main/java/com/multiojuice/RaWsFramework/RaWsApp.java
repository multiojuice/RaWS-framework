package com.multiojuice.RaWsFramework;

import com.multiojuice.RaWsFramework.Controllers.HTTPController;
import com.multiojuice.RaWsFramework.Controllers.WebSocketController;
import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.util.HashMap;

public class RaWsApp extends Thread {
    private HashMap<String, Resolver> httpEndpoints;
    private HTTPController httpController;

    private HashMap<String, Resolver> webSocketProtocols;
    private WebSocketController webSocketController;

    public RaWsApp(HashMap<String, Resolver> newHTTPEndpoints, HashMap<String, Resolver> newWebSocketProtocols) {
            httpEndpoints = newHTTPEndpoints;
            webSocketProtocols = newWebSocketProtocols;
    }

    public void run() {
        if(httpEndpoints != null) {
            httpController = new HTTPController(httpEndpoints);
            httpController.start();
        }

        if(webSocketProtocols != null) {
           webSocketController = new WebSocketController(webSocketProtocols);
           webSocketController.start();
        }
    }

}
