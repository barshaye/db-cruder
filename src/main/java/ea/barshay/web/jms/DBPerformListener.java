package ea.barshay.web.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "io.reflectoring.kafka.enabled", havingValue = "true")
public class DBPerformListener {
  private final ErrorSender errorSender;

  public DBPerformListener(ErrorSender errorSender) {
    this.errorSender = errorSender;
  }

  @KafkaListener(topics = "${io.reflectoring.kafka.topic-to-read-instructions}", id = "DBPerformListener", groupId = "db_cruder_consumer_group")
  public void listener(String message) {
    log.info("Listener [{}]", message);
    errorSender.sendMessage("{\"message\": \"Shit happens]\"}");
  }
}
