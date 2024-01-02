package thi.cnd.domain;

import thi.cnd.domain.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RideService {

  Ride createRide(String origin, String destination, LocalDate date, LocalTime time, String driver,
      String description, int capacity);

  Ride findRideById(String id);

  List<Ride> findAll();

  List<Ride> findRidesByDriver(String driver);

}
