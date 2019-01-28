package com.multiojuice.RaWsFramework.Resolvers;


public class CallResolver implements Runnable {
    private Resolver resolver;

    public CallResolver(Resolver resolver) {
        this.resolver = resolver;
    }

    public void run() {
        resolver.resolve();
    }
}
