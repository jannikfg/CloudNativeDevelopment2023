package thi.cnd.ports.outgoing;

import java.time.LocalDate;
import thi.cnd.domain.model.AveragePassengerCount;

public interface AveragePassengerCountRepository {
  void save(AveragePassengerCount averagePassengerCount);
  AveragePassengerCount getAveragePassengerCount(LocalDate date);
}
