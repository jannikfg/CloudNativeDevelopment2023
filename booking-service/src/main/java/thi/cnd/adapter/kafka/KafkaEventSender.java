package thi.cnd.adapter.kafka;

import java.util.Properties;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Slf4j
@NoArgsConstructor
@ApplicationScoped
public class KafkaEventSender {

  @Inject
  public KafkaEventSender(
      @ConfigProperty(name = "kafka.url") String bootstrapServers) {
    this.bootstrapServers = bootstrapServers;
  }

  private String bootstrapServers;

  public void sendMessageToTopic(String eventMessageAsString, String topic) {
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());

    // create the producer
    KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

    // create a producer record
    ProducerRecord<String, String> producerRecord =
        new ProducerRecord<>(topic, eventMessageAsString);

    // send data - asynchronous
    producer.send(producerRecord);

    // flush data - synchronous
    producer.flush();
    // flush and close producer
    producer.close();
  }
}
