package net.mfjassociates.websocket.controller;
import static net.mfjassociates.websocket.WebSocketApplication.TEMP_TRUST_STORE;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.tomcat.websocket.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import net.mfjassociates.websocket.SimpleTextWebSocketHandler;

@RestController
public class WebSocketController {
	
	private static final String KEYSTORE_NAME = "emptyStore.keystore";
	
	@Value("${"+TEMP_TRUST_STORE+"}")
	private String tempFolder;

    @GetMapping("/hello")
    public String hello() throws ExecutionException, InterruptedException, IOException {
        StandardWebSocketClient client = new StandardWebSocketClient();
        // copy the keystore from the classpath to a temp folder.  The SSL trust store must be a java.io.File
        URL ks = this.getClass().getResource("/"+KEYSTORE_NAME);
        Path ksp = Paths.get(tempFolder).resolve(KEYSTORE_NAME);
        try (InputStream in = ks.openStream()) {
            Files.copy(in, ksp, StandardCopyOption.REPLACE_EXISTING);
        }
        Map<String, Object> trustStoreProperties=new HashMap<>();
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PROPERTY, ksp.toAbsolutePath().toString());
        trustStoreProperties.put(Constants.SSL_TRUSTSTORE_PWD_PROPERTY, "storePassword");
        client.setUserProperties(trustStoreProperties);
        WebSocketSession session = client.doHandshake(new SimpleTextWebSocketHandler(), "wss://echo.websocket.org").get();

        session.sendMessage(new TextMessage("Hello Server"));

        return "WebSocket connection established and message sent";
    }

}
