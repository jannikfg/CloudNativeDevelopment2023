package thi.cnd.adapter.kafka;

public enum KafkaTopic {
  TOPIC_BOOKING_CREATED("booking-created"),
  TOPIC_RIDE_CREATED("ride-created");

  public String topic;

  KafkaTopic(String topic) {
    this.topic = topic;
  }
}
