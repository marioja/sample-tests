package tajdt;

import static org.junit.jupiter.api.Assertions.fail;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

public class MainTest {

	@Mock Appender appender;
	@Captor ArgumentCaptor<LogEvent> logCaptor;
	private Main ma;
	
	@BeforeEach
	public void setup() {
		ma=new Main();
	}

	@Test
	public void testDoGreetings() {
		ma.doGreetings("Peter");
		ma.doGreetings("Tommy");
		ma.doGreetings("Sylvia");
	}

}
