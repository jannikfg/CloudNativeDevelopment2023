package thi.cnd.adapter.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import thi.cnd.adapter.jpa.entities.BookingEntity;
import thi.cnd.adapter.jpa.entities.RideEntity;
import thi.cnd.domain.model.Booking;
import thi.cnd.ports.outgoing.BookingRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaBookingRepositoryImpl implements BookingRepository {

  @Inject
  JpaBookingRepository jpaBookingRepository;

  @Inject
  JpaRideRepository jpaRideRepository;

  @Override
  public void save(Booking booking) {
    BookingEntity bookingEntity = new BookingEntity();
    RideEntity rideEntity =
        jpaRideRepository.findByIdOptional(booking.getRide().getId().toString()).orElse(null);
    bookingEntity.setId(booking.getId().toString());
    bookingEntity.setUserId(booking.getUser());
    bookingEntity.setDate(booking.getDate());
    bookingEntity.setRide(rideEntity);
    jpaBookingRepository.persist(bookingEntity);
  }

  @Override
  public Booking findById(String id) {
    Optional<BookingEntity> bookingEntity = jpaBookingRepository.findByIdOptional(id);
    return bookingEntity.map(BookingEntity::toBooking).orElse(null);
  }

  @Override
  public List<Booking> findBookingsByUser(String user) {
    List<BookingEntity> bookingEntities = jpaBookingRepository.listAll();
    return bookingEntities.stream().filter(bookingEntity -> user.equals(bookingEntity.getUserId()))
        .toList().stream().map(BookingEntity::toBooking).toList();
  }

  @Override
  public List<Booking> findAll() {
    return jpaBookingRepository.listAll().stream().map(BookingEntity::toBooking).toList();
  }

  @Override
  public List<Booking> findBookingsByRideId(String rideId) {
    List<BookingEntity> bookingEntities = jpaBookingRepository.listAll();
    return bookingEntities.stream()
        .filter(bookingEntity -> rideId.equals(bookingEntity.getRide().getId())).toList().stream()
        .map(BookingEntity::toBooking).toList();
  }
}
