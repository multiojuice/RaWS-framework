package com.multiojuice.RaWsFramework;

import com.multiojuice.RaWsFramework.Controllers.HTTPController;
import com.multiojuice.RaWsFramework.Controllers.WebSocketController;
import com.multiojuice.RaWsFramework.Resolvers.Resolver;

import java.util.HashMap;

public class RaWsApp implements Runnable {
    private HashMap<String, Resolver> httpEndpoints;
    private Thread httpController;

    private HashMap<String, Resolver> webSocketProtocols;
    private Thread webSocketController;

    public RaWsApp(HashMap<String, Resolver> newHTTPEndpoints, HashMap<String, Resolver> newWebSocketProtocols) {
            httpEndpoints = newHTTPEndpoints;
            webSocketProtocols = newWebSocketProtocols;
    }

    public void run() {
        if(httpEndpoints != null) {
            HTTPController httpControllerInstance = new HTTPController(httpEndpoints);
            httpController = new Thread(httpControllerInstance);
            httpController.start();
        }

        if(webSocketProtocols != null) {
           WebSocketController webSocketControllerInstance = new WebSocketController(webSocketProtocols);
           webSocketController = new Thread(webSocketControllerInstance);
           webSocketController.start();
        }
    }

}
