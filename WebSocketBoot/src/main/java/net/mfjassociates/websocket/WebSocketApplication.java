package net.mfjassociates.websocket;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketApplication {
	
	public static final String TEMP_TRUST_STORE = "trust-store-temp-folder";

	public static void main(String[] args) throws IOException {
		SpringApplication app=new SpringApplication(WebSocketApplication.class);
		Path tempDirectory = Files.createTempDirectory("trust");
		tempDirectory.toFile().deleteOnExit();
		Properties defaults=new Properties();
		defaults.put(TEMP_TRUST_STORE, tempDirectory.toString());
		app.setDefaultProperties(defaults);
		app.run(args);
	}
}
