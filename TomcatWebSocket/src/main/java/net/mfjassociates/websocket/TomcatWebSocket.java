package net.mfjassociates.websocket;

import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class TomcatWebSocket {
    private static Object waitLock = new Object();

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendText("Hello");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnMessage
    public void onMessage(String message) throws InterruptedException {
        System.out.println("Received msg: " + message);
        synchronized(waitLock) {
        	waitLock.wait();
        }
    }

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            Session session = container.connectToServer(TomcatWebSocket.class, new URI("ws://echo.websocket.org"));
            synchronized(waitLock) {
                waitLock.wait();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}