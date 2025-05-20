package ea.barshay;

import io.opentelemetry.sdk.trace.IdGenerator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class App {
  private static final Logger LOG = LoggerFactory.getLogger("LOGSTASH");
  public static void main(String[] args) {
      SpringApplication.run(App.class, args);
    try {
      String[] identifiers = "00-12345678901234567890123456789012-1234567890123456-01".split("-");
      IdGenerator idGenerator = IdGenerator.random();
      MDC.put("trace_id", identifiers[1]);
      MDC.put("span_id", idGenerator.generateSpanId());
      MDC.put("par_span_id", identifiers[2]);
      MDC.put("step", "7");
      MDC.put("prc_name", "SendForm");
      MDC.put("prc_id", "abcdef1234567890");
      MDC.put("date", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
      MDC.put("msg_id", "");
      MDC.put("service", "bprGetForm");
      MDC.put("step_name", "Получение заявки в БПР");
      MDC.put("system", "BPR");
      MDC.put("type", "Request");
      MDC.put("host", "sas-rtdm1-test");
      MDC.put("error", null);
      LOG.info("Принято сообщение в систему Новый блок принятия решений {}", LocalDateTime.now());
    }finally {
      MDC.clear();
    }
  }
}
