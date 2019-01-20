package com.multiojuice.RaWsFramework;

public class HTTPMethodsResolver implements Resolver {
    @Override
    public void resolve(RequestType requestType) {
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

