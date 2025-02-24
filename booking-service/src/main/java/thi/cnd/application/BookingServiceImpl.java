package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import thi.cnd.domain.BookingService;
import thi.cnd.domain.model.Booking;
import thi.cnd.domain.model.Ride;
import thi.cnd.ports.outgoing.BookingEvents;
import thi.cnd.ports.outgoing.BookingRepository;
import thi.cnd.ports.outgoing.RideEvents;
import thi.cnd.ports.outgoing.RideRepository;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BookingServiceImpl implements BookingService {

  @Inject
  BookingRepository bookingRepository;

  @Inject
  RideRepository rideRepository;

  @Inject
  BookingEvents bookingEvents;

  @Override
  public Booking createBooking(String user, LocalDate date, String rideId) throws Exception {
    Ride ride = rideRepository.findById(rideId);
    List<Booking> bookingsByRideId = bookingRepository.findBookingsByRideId(rideId);
    if (bookingsByRideId.size() < ride.getCapacity()) {
      Booking booking = new Booking();
      booking.setUser(user);
      booking.setDate(date);
      booking.setRide(ride);
      bookingRepository.save(booking);
      bookingEvents.publishBookingCreatedEvent(booking);
      return booking;
    } else {
      throw new Exception("Ride has no capacity"); // TODO: Logik hierfür implementieren
    }
  }

  @Override
  public Booking findBookingById(String id) {
    return bookingRepository.findById(id);
  }

  @Override
  public List<Booking> findBookingByUser(String user) {
    return bookingRepository.findBookingsByUser(user);
  }

  @Override
  public List<Booking> findAll() {
    return bookingRepository.findAll();
  }
}
