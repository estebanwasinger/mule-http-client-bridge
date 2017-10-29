package com.github.estebanwasinger.http.bridge.api;

import org.mule.module.http.internal.request.HttpClient;

public interface HttpClientBridgeFactory {

    HttpClient createClient(int maxConnections, boolean usePersistentConnections, int connectionIdleTimeout, String threadNamePrefix, String ownerName);
}
