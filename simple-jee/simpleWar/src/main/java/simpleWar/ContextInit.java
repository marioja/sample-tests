package simpleWar;

import java.io.IOException;
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

import simpleUtil.ContextUtil;

@SuppressWarnings("unused")
@WebListener
public class ContextInit implements ServletContextListener {
	
	private static final Logger logger;
	private static Properties theP=ContextUtil.getContext(ContextInit.class.getClassLoader());
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
		System.out.println("Properties= "+theP.toString());
//		getContext();
//		getContext(this.getClass().getClassLoader());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing t do

	}
	public static void main(String[] args) {
		Properties p=new Properties();
		ContextUtil.getContext(ContextInit.class.getClassLoader());
		logger.info("You are {} and I am {}", 4, 6);
	}
}
