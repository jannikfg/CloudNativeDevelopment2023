package thi.cnd.application;

import java.time.LocalDate;
import java.util.List;
import thi.cnd.domain.RidesPerDayService;
import thi.cnd.domain.model.RidesPerDay;

public class AnalyticsServiceImpl implements RidesPerDayService {

  @Override
  public RidesPerDay getRidesPerDay(LocalDate date) {
    return null;
  }

  @Override
  public List<RidesPerDay> getRidesPerDayInPeriod(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public List<RidesPerDay> getAllRidesPerDay() {
    return null;
  }
}
