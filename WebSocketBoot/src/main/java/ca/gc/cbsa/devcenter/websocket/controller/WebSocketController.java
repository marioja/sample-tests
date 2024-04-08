package ca.gc.cbsa.devcenter.websocket.controller;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import ca.gc.cbsa.devcenter.websocket.SimpleTextWebSocketHandler;

@RestController
public class WebSocketController {

    @GetMapping("/hello")
    public String hello() throws ExecutionException, InterruptedException, IOException {
        StandardWebSocketClient client = new StandardWebSocketClient();
        WebSocketSession session = client.doHandshake(new SimpleTextWebSocketHandler(), "wss://echo.websocket.org").get();

        session.sendMessage(new TextMessage("Hello Server"));

        return "WebSocket connection established and message sent";
    }

}
