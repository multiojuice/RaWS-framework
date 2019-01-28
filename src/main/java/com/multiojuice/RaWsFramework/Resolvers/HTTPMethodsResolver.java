package com.multiojuice.RaWsFramework.Resolvers;

import com.multiojuice.RaWsFramework.RequestType;

import java.io.PrintWriter;


public class HTTPMethodsResolver implements Resolver {
    protected PrintWriter printWriter;
    protected RequestType requestType;

    public HTTPMethodsResolver(RequestType requestType, PrintWriter printWriter) {
        this.requestType = requestType;
        this.printWriter = printWriter;
    }

    @Override
    public void resolve() {
        switch (requestType) {
            case GET:
                getResolve();
                break;
            case PUT:
                putResolve();
                break;
            case POST:
                postResolve();
                break;
            case PATCH:
                patchResolve();
                break;
            case DELETE:
                deleteResolve();
                break;
        }
    }

    public PrintWriter getPrintWriter() {
        return printWriter;
    }

    public void setPrintWriter(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public void getResolve() {
        System.out.println("No custom GET resolve written for this endpoint");
    }

    public void putResolve() {
        System.out.println("No custom PUT resolve written for this endpoint");
    }

    public void postResolve() {
        System.out.println("No custom POST resolve written for this endpoint");
    }

    public void patchResolve() {
        System.out.println("No custom PATCH resolve written for this endpoint");
    }

    public void deleteResolve() {
        System.out.println("No custom DELETE resolve written for this endpoint");
    }

}

