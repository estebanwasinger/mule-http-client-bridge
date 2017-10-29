package com.github.estebanwasinger.http.bridge.api;

import org.mule.api.MuleContext;
import org.mule.module.http.internal.request.HttpClient;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class DefaultHttpClientBridgeFactory implements HttpClientBridgeFactory {

    private final ArrayList<String> factories;
    private MuleContext muleContext;

    public DefaultHttpClientBridgeFactory(MuleContext muleContext) {
        this.muleContext = muleContext;
        this.factories = new ArrayList<String>();

        factories.add("com.github.estebanwasinger.http.bridge.api.HttpClientFactory380");
        factories.add("com.github.estebanwasinger.http.bridge.api.HttpClientFactory370");
        factories.add("com.github.estebanwasinger.http.bridge.api.HttpClientFactory360");
    }

    public HttpClient createClient(int maxConnections, boolean usePersistentConnections, int connectionIdleTimeout, String threadNamePrefix, String ownerName) {
        HttpClientBridgeFactory bridgeFactory;

        bridgeFactory = getHttpClientBridgeFactory();

        if(bridgeFactory == null) {
            throw new RuntimeException("Unable to create the HTTP Client");
        }

        return bridgeFactory.createClient(maxConnections, usePersistentConnections, connectionIdleTimeout, threadNamePrefix, ownerName);
    }

    private HttpClientBridgeFactory getHttpClientBridgeFactory() {
        for (String factoryClass : factories) {
            try{
                Class<?> aClass = Thread.currentThread().getContextClassLoader().loadClass(factoryClass);
                Constructor<?> constructor = aClass.getConstructor(MuleContext.class);
                return (HttpClientBridgeFactory) constructor.newInstance(muleContext);
            } catch (Exception e){
                System.out.println("Unable to create: " + factoryClass);
            }
        }
        return null;
    }
}
