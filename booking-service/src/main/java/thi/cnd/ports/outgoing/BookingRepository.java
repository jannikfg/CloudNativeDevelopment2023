package thi.cnd.ports.outgoing;

import thi.cnd.domain.model.Booking;

import java.util.List;

public interface BookingRepository {

  void save(Booking booking);

  Booking findById(String id);

  List<Booking> findBookingsByUser(String user);

  List<Booking> findAll();

  List<Booking> findBookingsByRideId(String rideId);

}
