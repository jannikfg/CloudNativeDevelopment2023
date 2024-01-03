package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;
import thi.cnd.domain.AveragePassengerCountService;
import thi.cnd.domain.RidesPerDayService;
import thi.cnd.domain.RidesPerLocationService;
import thi.cnd.domain.model.AveragePassengerCount;
import thi.cnd.domain.model.RidesPerLocation;

@ApplicationScoped
public class AnalyticsServiceImpl implements RidesPerDayService, RidesPerLocationService,
    AveragePassengerCountService {

  @Override
  public thi.cnd.domain.model.RidesPerDay getRidesPerDay(LocalDate date) {
    return null;
  }

  @Override
  public List<thi.cnd.domain.model.RidesPerDay> getRidesPerDayInPeriod(LocalDate startDate, LocalDate endDate) {
    return null;
  }

  @Override
  public List<thi.cnd.domain.model.RidesPerDay> getAllRidesPerDay() {
    return null;
  }

  @Override
  public RidesPerLocation getRidesPerLocation(String location) {
    return null;
  }

  @Override
  public List<RidesPerLocation> getAllRidesPerLocation() {
    return null;
  }

  @Override
  public AveragePassengerCount getAveragePassengerCountPerDay(LocalDate date) {
    return null;
  }

  @Override
  public List<AveragePassengerCount> getAllAveragePassengerCounts() {
    return null;
  }
}
