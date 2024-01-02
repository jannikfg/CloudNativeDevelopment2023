package thi.cnd.ports.outgoing;

import thi.cnd.domain.model.Ride;

import java.util.List;

public interface RideRepository {
  void save(Ride ride);

  Ride findById(String id);

  List<Ride> findAll();

  List<Ride> findByDriver(String driver);
}
