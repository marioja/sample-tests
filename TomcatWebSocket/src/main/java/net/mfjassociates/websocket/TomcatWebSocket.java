package net.mfjassociates.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.tomcat.websocket.Constants;

//@ClientEndpoint
public class TomcatWebSocket extends Endpoint {
    private static Object waitLock = new Object();

    @Override
    public void onOpen(Session session, EndpointConfig config) {
        try {
            session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    // This method is called whenever a message is received
                    System.out.println("Received message: " + message);
                }
            });
            System.out.println("Sending message");
            session.getBasicRemote().sendText("Hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    
    private static final String KEYSTORE_NAME = "openJdk17Cacerts";

    public static void main(String[] args) throws IOException {
        Path tempDirectory = Files.createTempDirectory("trust");
        tempDirectory.toFile().deleteOnExit();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        // Create a configuration object
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().decoders(null).encoders(null).build();

        // copy the keystore from the classpath to a temp folder.  The SSL trust store must be a java.io.File
        URL ks = TomcatWebSocket.class.getResource("/"+KEYSTORE_NAME);
        Path ksp = tempDirectory.resolve(KEYSTORE_NAME);
        try (InputStream in = ks.openStream()) {
            Files.copy(in, ksp, StandardCopyOption.REPLACE_EXISTING);
        }
        // Set user properties
        Map<String, Object> trustStoreProperties=config.getUserProperties();
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PROPERTY, ksp.toAbsolutePath().toString());
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PWD_PROPERTY, "changeit");
        try {
            Session session = container.connectToServer(TomcatWebSocket.class, config, new URI("wss://echo.websocket.org"));
            synchronized(waitLock) {
                waitLock.wait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}