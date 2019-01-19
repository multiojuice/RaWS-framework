package com.multiojuice.RaWsFramework;

public class HTTPMethodsResolver implements Resolver {
    private Resolver getResolver;
    private Resolver postResolver;
    private Resolver putResolver;
    private Resolver patchResolver;
    private Resolver deleteResolver;

    @Override
    public void resolve(RequestType requestType) {
        switch (requestType) {
            case GET:
                if (getResolver != null)
                getResolver.resolve(requestType);
                break;
            case PUT:
                if (putResolver != null)
                putResolver.resolve(requestType);
                break;
            case POST:
                if (postResolver != null)
                postResolver.resolve(requestType);
                break;
            case PATCH:
                if (patchResolver != null)
                patchResolver.resolve(requestType);
                break;
            case DELETE:
                if (deleteResolver != null)
                deleteResolver.resolve(requestType);
                break;
        }
    }

    public Resolver getGetResolver() {
        return getResolver;
    }

    public void setGetResolver(Resolver getResolver) {
        this.getResolver = getResolver;
    }

    public Resolver getPostResolver() {
        return postResolver;
    }

    public void setPostResolver(Resolver postResolver) {
        this.postResolver = postResolver;
    }

    public Resolver getPutResolver() {
        return putResolver;
    }

    public void setPutResolver(Resolver putResolver) {
        this.putResolver = putResolver;
    }

    public Resolver getPatchResolver() {
        return patchResolver;
    }

    public void setPatchResolver(Resolver patchResolver) {
        this.patchResolver = patchResolver;
    }

    public Resolver getDeleteResolver() {
        return deleteResolver;
    }

    public void setDeleteResolver(Resolver deleteResolver) {
        this.deleteResolver = deleteResolver;
    }
}

