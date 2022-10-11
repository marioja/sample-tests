package simpleWar;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

@SuppressWarnings("unused")
@WebListener
public class ContextInit implements ServletContextListener {
	
	private static final Logger logger;
	static {
		try {
			if (1==3) { // set to true to initialize programmatically (https://www.baeldung.com/log4j2-programmatic-config)
				ConfigurationBuilder<BuiltConfiguration> builder= ConfigurationBuilderFactory.newConfigurationBuilder();
				builder.add(builder.newAppender("stdout", "Console"));
				RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);
				rootLogger.add(builder.newAppenderRef("stdout"));
				builder.add(rootLogger);
				if (1==3) builder.writeXmlConfiguration(System.out);
				Configurator.initialize(builder.build());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		logger=LogManager.getLogger(ContextInit.class);
	}
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Properties p=new Properties();
		loadSuper(p, this.getClass().getClassLoader());
	}

	private static void loadSuper(Properties p, ClassLoader cl) {
		try {
			p.load(Objects.requireNonNull(cl.getResourceAsStream("dev/super.properties"), "problem loading property file super.properties"));
			logger.info("The guru for this location is {}", Objects.requireNonNull(p.getProperty("guru")));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing t do

	}
	public static void main(String[] args) {
		Properties p=new Properties();
		loadSuper(p, ContextInit.class.getClassLoader());
		logger.info("You are {} and I am {}", 4, 6);
	}
}
