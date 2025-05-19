package ea.barshay;

import io.opentelemetry.sdk.trace.IdGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.event.Level;

public class E2ELoggerTest {
  @BeforeAll
  static void setup() {
    System.setProperty("logging.config", "classpath:logback-spring.xml");
  }

  @Test
  void testLogging() {
    try {
      String [] identifiers =  "00-12345678901234567890123456789012-1234567890123456-01".split("-");
      IdGenerator idGenerator = IdGenerator.random();
      MDC.put("trace_id", identifiers[1]);
      MDC.put("span_id", idGenerator.generateSpanId());
      MDC.put("par_span_id", identifiers[2]);
      MDC.put("step","7");
      MDC.put("prc_name", "SendForm");
      MDC.put("prc_id","abcdef1234567890");
      MDC.put("date",  DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()));
      MDC.put("msg_id", "");
      MDC.put("service", "bprGetForm");
      MDC.put("step_name", "Получение заявки в БПР");
      MDC.put("system", "BPR");
      MDC.put("type",  "Request");
      MDC.put("host", "sas-rtdm1-test");
      MDC.put("error", null);

      //log(LoggerFactory.getLogger("E2E_ELASTIC"));
      log(LoggerFactory.getLogger("FILEROLLING"));
      //log(LoggerFactory.getLogger("CONSOLE"));
    } finally {
      MDC.clear();
    }
  }
  private void log(Logger logger) {
    Assertions.assertNotNull(logger);
    Assertions.assertTrue(logger.isEnabledForLevel(Level.DEBUG));
    Assertions.assertFalse(logger.isEnabledForLevel(Level.DEBUG));
    logger.info("Принято сообщение в систему Новый блок принятия решений");

  }
}
