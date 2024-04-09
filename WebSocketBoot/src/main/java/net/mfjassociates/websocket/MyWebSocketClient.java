package net.mfjassociates.websocket;
import javax.net.ssl.SSLContext;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.io.FileInputStream;
import java.security.KeyStore;

public class MyWebSocketClient {

    public static void main(String[] args) throws Exception {
        // Load the custom truststore
        String truststorePath = "path/to/my-truststore.jks";
        char[] truststorePassword = "your-truststore-password".toCharArray();

        KeyStore truststore = KeyStore.getInstance("JKS");
        truststore.load(new FileInputStream(truststorePath), truststorePassword);

        // Create an SSL context with the custom truststore
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, null, null);
        sslContext.init(null, null, null, new MyTrustManager(truststore));

        // Configure the WebSocket container
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        container.setDefaultMaxSessionIdleTimeout(5000); // Set other properties as needed

        // Set the custom SSL context
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create()
                .configurator(new MyClientEndpointConfigurator(sslContext))
                .build();

        // Connect to the WebSocket server
        container.connectToServer(MyWebSocketClientEndpoint.class, config);
    }
}
