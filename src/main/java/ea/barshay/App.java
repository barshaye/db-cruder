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
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  public static void main(String[] args) {
      SpringApplication.run(App.class, args);
    LOG.info("Принято сообщение в систему Новый блок принятия решений {}", LocalDateTime.now());
  }
}
