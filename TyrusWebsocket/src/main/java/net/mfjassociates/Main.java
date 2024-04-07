package net.mfjassociates;
import javax.websocket.ClientEndpoint;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ContainerProvider;

@ClientEndpoint
public class Main {
	
	private static CountDownLatch latch=null;

    @OnMessage
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        latch.countDown(); // decrement the count, releasing it if it reaches zero
    }

    public static void main(String[] args) {

    	latch = new CountDownLatch(1);
        try {
            // Create a new WebSocket container
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();

            // Connect to the server endpoint
            String uri = "ws://localhost:8080/websocketendpoint";
            uri = "wss://echo.websocket.org";
            Session session = container.connectToServer(Main.class, URI.create(uri));

            // Send a message to the server endpoint
            session.getBasicRemote().sendText(/*"Hello WebSocket Server!"*/"Hello, server!");

            // Wait for the message from the server with a timeout
            if (!latch.await(30, TimeUnit.SECONDS)) {
                System.out.println("No response received from the server within the timeout period.");
            }
            
            // Close the connection
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
