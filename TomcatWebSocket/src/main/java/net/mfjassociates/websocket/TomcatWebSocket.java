package net.mfjassociates.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.ClientEndpointConfig;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.tomcat.websocket.Constants;

public class TomcatWebSocket extends Endpoint {
    private static final Object waitLock = new Object();
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void onOpen(Session session, EndpointConfig config) {
    	try {
    		final String helloMessage="hello";
    		MessageHandler.Whole<String> mh= new MessageHandler.Whole<String>() { //NOSONAR addMessageHandler will fail if this is a lambda
    			@Override
    			public void onMessage(String message) {
    				// This method is called whenever a message is received
    				logger.info("Received message: {}", message);
    				if (helloMessage.equals(message)) {
    					synchronized(waitLock) {
    						waitLock.notifyAll();
    					}
    				}
    			}
    		};
    		session.addMessageHandler(mh);
    		logger.info("Sending message: {}", helloMessage);
    		session.getBasicRemote().sendText(helloMessage);
    	} catch (IOException e) {
    		throw new IllegalStateException(e);
    	}
    }

    
    private static final String KEYSTORE_NAME = "openJdk17Cacerts";
    private static final Map<String, String> passwords=new HashMap<>();
    static {
    	passwords.put("emptyStore.keystore", "storePassword");
    	passwords.put("openJdk17Cacerts", "changeit");
    }
    

    public static void main(String[] args) throws IOException, DeploymentException, URISyntaxException, InterruptedException {
    	Configurator.setRootLevel(Level.INFO);
        Path tempDirectory = Files.createTempDirectory("");
        tempDirectory.toFile().deleteOnExit();
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        // Create a configuration object
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();

        // copy the keystore from the classpath to a temp folder.  The SSL trust store must be a java.io.File
        URL ks = TomcatWebSocket.class.getResource("/"+KEYSTORE_NAME);
        Path ksp = Files.createTempFile(tempDirectory, "", "");
        tempDirectory.toFile().deleteOnExit();
        ksp.toFile().deleteOnExit();
        try (InputStream in = ks.openStream()) {
            Files.copy(in, ksp, StandardCopyOption.REPLACE_EXISTING);
        }
        // Set user properties
        Map<String, Object> trustStoreProperties=config.getUserProperties();
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PROPERTY, ksp.toAbsolutePath().toString());
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PWD_PROPERTY, passwords.get(KEYSTORE_NAME));
        Session session = container.connectToServer(TomcatWebSocket.class, config, new URI("wss://echo.websocket.org"));
        logger.info(session.getNegotiatedSubprotocol());
        synchronized(waitLock) {
            waitLock.wait();
        }
        logger.info("Finished processing message");
    }
}