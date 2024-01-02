package thi.cnd.adapter.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import thi.cnd.adapter.jpa.entities.RideEntity;
import thi.cnd.domain.model.Ride;
import thi.cnd.ports.outgoing.RideRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaRideRepositoryImpl implements RideRepository {

  @Inject
  JpaRideRepository jpaRideRepository;

  @Override
  public void save(Ride ride) {
    RideEntity rideEntity = new RideEntity();
    rideEntity.setId(ride.getId().toString());
    rideEntity.setOrigin(ride.getOrigin());
    rideEntity.setDestination(ride.getDestination());
    rideEntity.setDate(ride.getDate());
    rideEntity.setTime(ride.getTime());
    rideEntity.setDriver(ride.getDriver());
    rideEntity.setDescription(ride.getDescription());
    rideEntity.setCapacity(ride.getCapacity());
    jpaRideRepository.persist(rideEntity);
  }

  @Override
  public Ride findById(String id) {
    Optional<RideEntity> rideEntity = jpaRideRepository.findByIdOptional(id);
    return rideEntity.map(RideEntity::toRide).orElse(null);
  }

  @Override
  public List<Ride> findAll() {
    return jpaRideRepository.listAll().stream().map(RideEntity::toRide).toList();
  }

  @Override
  public List<Ride> findByDriver(String driver) {
    List<RideEntity> rideEntities = jpaRideRepository.listAll();
    return rideEntities.stream().filter(rideEntity -> driver.equals(rideEntity.getDriver()))
        .toList().stream().map(RideEntity::toRide).toList();
  }

}
