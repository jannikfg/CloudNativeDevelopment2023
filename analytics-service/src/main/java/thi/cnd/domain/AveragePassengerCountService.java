package thi.cnd.domain;

import java.time.LocalDate;
import java.util.List;
import thi.cnd.domain.model.AveragePassengerCount;

public interface AveragePassengerCountService {
  AveragePassengerCount getAveragePassengerCountPerDay (LocalDate date);

  List<AveragePassengerCount> getAllAveragePassengerCounts();

}
