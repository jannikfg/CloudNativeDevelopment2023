package thi.cnd.adapter.api.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import thi.cnd.adapter.api.jpa.entities.RidesPerDayEntity;
import thi.cnd.domain.model.RidesPerDay;
import thi.cnd.ports.outgoing.RidesPerDayRepository;

@ApplicationScoped
public class JpaRidesPerDayRepositoryImpl implements RidesPerDayRepository {

  @Inject
  JpaRidesPerDayRepository jpaRidesPerDayRepository;
  @Override
  public void save(RidesPerDay ridesPerDay) {
    RidesPerDayEntity ridesPerDayEntity = new RidesPerDayEntity();
    ridesPerDayEntity.setDate(ridesPerDay.getDate());
    ridesPerDayEntity.setRidesPerDay(ridesPerDay.getRidesPerDay());
    jpaRidesPerDayRepository.persist(ridesPerDayEntity);
  }

  @Override
  public RidesPerDay getRidesPerDay(LocalDate date) {
    return jpaRidesPerDayRepository.findByIdOptional(date).map(RidesPerDayEntity::toDomainModel).orElse(null);
  }
}
