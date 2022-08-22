package simpleWar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GreatWar {
	
	private static Logger logger=LoggerFactory.getLogger(GreatWar.class);
	
	private GreatWar() {		
	}

	public static void displayHello(String name) {
		logger.info("Good day: "+name);
	}
}
