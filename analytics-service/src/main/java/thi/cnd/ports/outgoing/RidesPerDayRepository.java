package thi.cnd.ports.outgoing;

import java.time.LocalDate;
import thi.cnd.domain.model.RidesPerDay;

public interface RidesPerDayRepository {
  void save(RidesPerDay ridesPerDay);

  RidesPerDay getRidesPerDay(LocalDate date);
}
