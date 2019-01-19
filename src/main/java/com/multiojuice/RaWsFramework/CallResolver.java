package com.multiojuice.RaWsFramework;

public class CallResolver extends Thread {
    private Resolver resolver;
    private RequestType requestType;
    public CallResolver(Resolver newResolver, RequestType newRequestType) {
        resolver = newResolver;
        requestType = newRequestType;
    }

    public void run() {
        resolver.resolve(requestType);
    }
}
