package net.mfjassociates;
import javax.net.ssl.*;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

// ...

public class Main {

    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws IOException {
        try {
            // Create a custom SSLContext
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[]{}; }
            }}, new SecureRandom());

            // Connect to a secure WebSocket server
            WebSocketClient client = new WebSocketClient(new URI("wss://echo.websocket.org"), new Draft_6455(), null, 0) {

                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("Connected to server");
                    send("Hello, server!");
                }

                @Override
                public void onMessage(String s) {
                    // Handle incoming messages
                    System.out.println("Received: " + s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("Connection closed");
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            };

            // Set the custom SSLContext
            client.setSocket(sslContext.getSocketFactory().createSocket());

            // Connect to the server
            client.connect();

        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}