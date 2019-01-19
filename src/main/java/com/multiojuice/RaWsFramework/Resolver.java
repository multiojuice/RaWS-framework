package com.multiojuice.RaWsFramework;

enum RequestType {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
    WEB_SOCKET
}

public interface Resolver {
    public void resolve(RequestType requestType);
}
