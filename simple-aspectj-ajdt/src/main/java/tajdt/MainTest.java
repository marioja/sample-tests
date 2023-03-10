package tajdt;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

class MainTest {

	@Mock Appender appender;
	@Captor ArgumentCaptor<LogEvent> logCaptor;

	@Test
	void testDoGreetings() {
		fail("Not yet implemented");
	}

}
