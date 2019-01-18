package com.multiojuice.RaWsFramework;

public class CallResolver extends Thread {
    private Resolver resolver;

    public CallResolver(Resolver newResolver) {
        resolver = newResolver;
    }

    public void run() {
        resolver.resolve();
    }
}
