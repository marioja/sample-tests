package simpleUtil;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ContextUtil {
	
	private static Logger logger = LogManager.getLogger(ContextUtil.class);
	private ContextUtil() {
		throw new UnsupportedOperationException("Do not instantiate, utility class");
	}
	
	public static Properties getContext() {
		return getContext(ContextUtil.class.getClassLoader());
		
	}
	public static Properties getContext(ClassLoader cl) {
		try {
			System.out.println("Classload= "+cl.toString());
			Properties p=new Properties();
			p.load(Objects.requireNonNull(cl.getResourceAsStream("dev/super.properties"), "problem loading property file super.properties"));
			logger.info("The guru for this location is {}", Objects.requireNonNull(p.getProperty("guru")));
			return p;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
