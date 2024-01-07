package thi.cnd.adapter.kafka;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import thi.cnd.adapter.kafka.dto.BookingCreatedEvent;
import thi.cnd.adapter.kafka.dto.RideCreatedEvent;
import thi.cnd.domain.model.Booking;
import thi.cnd.domain.model.Ride;
import thi.cnd.ports.outgoing.BookingEvents;
import thi.cnd.ports.outgoing.RideEvents;


@ApplicationScoped
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class MessagingServiceImpl implements BookingEvents, RideEvents {

  @Inject
  KafkaEventSender kafkaEventSender;

  @Override
  public void publishBookingCreatedEvent(Booking booking) {
    kafkaEventSender.sendMessageToTopic(BookingCreatedEvent.fromDomainObject(booking).toString(), KafkaTopic.TOPIC_BOOKING_CREATED.topic);
  }



  @Override
  public void publishRideCreatedEvent(Ride ride) {
    kafkaEventSender.sendMessageToTopic(RideCreatedEvent.fromDomainObject(ride).toString(), KafkaTopic.TOPIC_RIDE_CREATED.topic);

  }
}
