package com.multiojuice.RaWsFramework;

import com.multiojuice.RaWsFramework.Resolvers.Resolver;
import com.multiojuice.RaWsFramework.Threads.HTTPHandlerThread;

import java.util.HashMap;

public class RaWsApp extends Thread {
    private HashMap<String, Resolver> HTTPEndpoints;
    private HTTPHandlerThread HTTPHandlerThread;

    public RaWsApp(HashMap<String, Resolver> newHTTPEndpoints) {
            HTTPEndpoints = newHTTPEndpoints;
    }

    public void run() {
        if(HTTPEndpoints != null) {
            HTTPHandlerThread = new HTTPHandlerThread(HTTPEndpoints);
            HTTPHandlerThread.start();
        }
    }

}
