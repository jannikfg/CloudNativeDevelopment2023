package thi.cnd.domain;

import thi.cnd.domain.model.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

  Booking createBooking(String user, LocalDate date, String rideId) throws Exception;

  Booking findBookingById(String id);

  List<Booking> findBookingByUser(String user);

  List<Booking> findAll();

}
