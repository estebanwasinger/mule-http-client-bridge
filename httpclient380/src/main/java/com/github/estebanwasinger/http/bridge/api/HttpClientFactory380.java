package com.github.estebanwasinger.http.bridge.api;

import static org.mule.module.http.internal.request.DefaultHttpRequesterConfig.OBJECT_HTTP_CLIENT_FACTORY;

import org.mule.api.MuleContext;
import org.mule.module.http.internal.request.HttpClient;
import org.mule.module.http.internal.request.HttpClientConfiguration;
import org.mule.module.http.internal.request.HttpClientFactory;
import org.mule.module.http.internal.request.grizzly.GrizzlyHttpClient;

public class HttpClientFactory380 implements HttpClientBridgeFactory{

    private MuleContext muleContext;

    public HttpClientFactory380(MuleContext muleContext) {
        this.muleContext = muleContext;
        Object o = HttpClientConfiguration.Builder.class;
        o = HttpClientFactory.class;
    }

    public HttpClient createClient(int maxConnections, boolean usePersistentConnections, int connectionIdleTimeout, String threadNamePrefix, String ownerName) {
        HttpClientConfiguration configuration = new HttpClientConfiguration.Builder()
                .setMaxConnections(maxConnections)
                .setUsePersistentConnections(usePersistentConnections)
                .setConnectionIdleTimeout(connectionIdleTimeout)
                .setThreadNamePrefix(threadNamePrefix)
                .setOwnerName(ownerName)
                .build();

        HttpClientFactory httpClientFactory = muleContext.getRegistry().get(OBJECT_HTTP_CLIENT_FACTORY);
        if (httpClientFactory == null) {
            return new GrizzlyHttpClient(configuration);
        } else {
            return httpClientFactory.create(configuration);
        }
    }
}
