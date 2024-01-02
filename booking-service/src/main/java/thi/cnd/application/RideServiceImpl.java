package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import thi.cnd.domain.RideService;
import thi.cnd.domain.model.Ride;
import thi.cnd.ports.outgoing.RideRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@ApplicationScoped
public class RideServiceImpl implements RideService {

  @Inject
  RideRepository rideRepository;

  @Override
  public Ride createRide(String origin, String destination, LocalDate date, LocalTime time,
      String driver, String description, int capacity) {
    Ride ride = new Ride();
    ride.setOrigin(origin);
    ride.setDestination(destination);
    ride.setDate(date);
    ride.setTime(time);
    ride.setDriver(driver);
    ride.setDescription(description);
    ride.setCapacity(capacity);
    rideRepository.save(ride);
    return ride;
  }

  @Override
  public Ride findRideById(String id) {
    return rideRepository.findById(id);
  }

  @Override
  public List<Ride> findAll() {
    return rideRepository.findAll();
  }

  @Override
  public List<Ride> findRidesByDriver(String driver) {
    return rideRepository.findByDriver(driver);
  }
}
