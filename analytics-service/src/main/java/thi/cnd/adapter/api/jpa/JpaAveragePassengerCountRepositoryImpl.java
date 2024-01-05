package thi.cnd.adapter.api.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import thi.cnd.adapter.api.jpa.entities.AveragePassengerCountEntity;
import thi.cnd.domain.model.AveragePassengerCount;
import thi.cnd.ports.outgoing.AveragePassengerCountRepository;

@ApplicationScoped
public class JpaAveragePassengerCountRepositoryImpl implements AveragePassengerCountRepository{

  @Inject
  JpaAveragePassengerCountRepository jpaAveragePassengerCountRepository;
  @Override
  public void save(AveragePassengerCount averagePassengerCount) {
    AveragePassengerCountEntity averagePassengerCountEntity = new AveragePassengerCountEntity();
    averagePassengerCountEntity.setDate(averagePassengerCount.getDate());
    averagePassengerCountEntity.setAveragePassengerCount(averagePassengerCount.getAveragePassengerCount());
    jpaAveragePassengerCountRepository.persist(averagePassengerCountEntity);
  }

  @Override
  public AveragePassengerCount getAveragePassengerCount(LocalDate date) {
    return jpaAveragePassengerCountRepository.findByIdOptional(date).map(AveragePassengerCountEntity::toDomainModel).orElse(null);
  }

}
