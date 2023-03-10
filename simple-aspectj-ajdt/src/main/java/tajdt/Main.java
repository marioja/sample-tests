package tajdt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	protected static final Logger logger=LogManager.getLogger();
	
	public void doGreetings(String message) {
		logger.info("Greetings sir: "+message);
	}

	public static void main(String[] args) {
		Main ma=new Main();
		ma.doGreetings("Charlie");
	}
}
