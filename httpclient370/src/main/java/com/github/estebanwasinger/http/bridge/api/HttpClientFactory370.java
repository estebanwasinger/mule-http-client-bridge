package com.github.estebanwasinger.http.bridge.api;

import org.mule.api.MuleContext;
import org.mule.module.http.internal.request.HttpClient;
import org.mule.module.http.internal.request.grizzly.GrizzlyHttpClient;
import org.mule.module.http.internal.request.grizzly.GrizzlyHttpClientConfiguration;

public class HttpClientFactory370 implements HttpClientBridgeFactory {

    private MuleContext muleContext;

    public HttpClientFactory370(MuleContext muleContext) {
        this.muleContext = muleContext;
        Object o = GrizzlyHttpClientConfiguration.class;
    }

    public HttpClient createClient(int maxConnections, boolean usePersistentConnections, int connectionIdleTimeout, String threadNamePrefix, String ownerName) {
        GrizzlyHttpClientConfiguration configuration = new GrizzlyHttpClientConfiguration.Builder()
                .setMaxConnections(maxConnections)
                .setUsePersistentConnections(usePersistentConnections)
                .setConnectionIdleTimeout(connectionIdleTimeout)
                .setThreadNamePrefix(threadNamePrefix)
                .build();

        return new GrizzlyHttpClient(configuration);
    }
}
