package thi.cnd.ports.outgoing;

import thi.cnd.domain.model.Booking;

public interface BookingEvents {
  void publishBookingCreatedEvent(Booking booking);
}
