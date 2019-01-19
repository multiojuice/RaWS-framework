package com.multiojuice.RaWsFramework;

public class HTTPMethodsResolver implements Resolver {
    private Resolver GET;
    private Resolver POST;
    private Resolver PUT;
    private Resolver PATCH;
    private Resolver DELETE;

    @Override
    public void resolve(RequestType requestType) {

    }

    public Resolver getGET() {
        return GET;
    }

    public void setGET(Resolver GET) {
        this.GET = GET;
    }

    public Resolver getPOST() {
        return POST;
    }

    public void setPOST(Resolver POST) {
        this.POST = POST;
    }

    public Resolver getPUT() {
        return PUT;
    }

    public void setPUT(Resolver PUT) {
        this.PUT = PUT;
    }

    public Resolver getPATCH() {
        return PATCH;
    }

    public void setPATCH(Resolver PATCH) {
        this.PATCH = PATCH;
    }

    public Resolver getDELETE() {
        return DELETE;
    }

    public void setDELETE(Resolver DELETE) {
        this.DELETE = DELETE;
    }
}

