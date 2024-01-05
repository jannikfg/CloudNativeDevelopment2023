package thi.cnd.adapter.api.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import thi.cnd.adapter.api.jpa.entities.RidesPerLocationEntity;
import thi.cnd.domain.model.RidesPerLocation;
import thi.cnd.ports.outgoing.RidesPerLocationRepository;

@ApplicationScoped
public class JpaRidesPerLocationRepositoryImpl implements RidesPerLocationRepository {

  @Inject
  JpaRidesPerLocationRepository jpaRidesPerLocationRepository;
  @Override
  public void save(RidesPerLocation ridesPerLocation) {
    RidesPerLocationEntity ridesPerLocationEntity = new RidesPerLocationEntity();
    ridesPerLocationEntity.setLocation(ridesPerLocation.getLocation());
    ridesPerLocationEntity.setRidesPerLocation(ridesPerLocation.getRidesPerLocation());
    jpaRidesPerLocationRepository.persist(ridesPerLocationEntity);
  }

  @Override
  public RidesPerLocation getRidesPerLocation(String location) {
    Optional<RidesPerLocationEntity> ridesPerLocationEntity = jpaRidesPerLocationRepository.findByIdOptional(location);
    return ridesPerLocationEntity.map(RidesPerLocationEntity::toDomainModel).orElse(null);
  }


}
