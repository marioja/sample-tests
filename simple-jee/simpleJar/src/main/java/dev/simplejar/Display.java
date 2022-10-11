package dev.simplejar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Display {
	
	private static final Logger logger=LoggerFactory.getLogger(Display.class);
	
	private Display() {
	}

	public static String info(String args) {
		logger.info("Displaying supplied information: {}", args);
		return ("Displaying supplied information: " + args);
	}

}
