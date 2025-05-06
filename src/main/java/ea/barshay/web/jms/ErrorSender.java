package ea.barshay.web.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(name = "io.reflectoring.kafka.enabled", havingValue = "true")
public class ErrorSender {
  private final KafkaTemplate<String, String> kafkaErrorTemplate;

  public ErrorSender(KafkaTemplate<String, String> kafkaErrorTemplate) {
    this.kafkaErrorTemplate = kafkaErrorTemplate;
  }

  public void sendMessage(String message) {
      log.info("Sending : {}", message);
      kafkaErrorTemplate.sendDefault(message);
  }
}
