package com.multiojuice.RaWsFramework.Resolvers;

import com.multiojuice.RaWsFramework.RequestType;

import java.net.Socket;

public interface Resolver {
    void resolve(RequestType requestType);
    Socket getSocket();
    void setSocket(Socket socket);
}
