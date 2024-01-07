package thi.cnd.adapter.kafka.dto;

import io.quarkus.arc.All;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thi.cnd.domain.model.Booking;
import thi.cnd.domain.model.Ride;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookingCreatedEvent {
  private UUID idBooking;
  private String user;
  private LocalDate date;
  private UUID ride;

  public static BookingCreatedEvent fromDomainObject(Booking booking) {
    BookingCreatedEvent bookingCreatedEvent = new BookingCreatedEvent();
    bookingCreatedEvent.setIdBooking(booking.getId());
    bookingCreatedEvent.setUser(booking.getUser());
    bookingCreatedEvent.setDate(booking.getDate());
    bookingCreatedEvent.setRide(booking.getRide().getId());
    return bookingCreatedEvent;
  }


}
