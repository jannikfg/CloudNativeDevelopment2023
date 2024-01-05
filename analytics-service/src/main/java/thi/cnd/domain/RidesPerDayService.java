package thi.cnd.domain;

import java.time.LocalDate;
import java.util.List;
import thi.cnd.domain.model.RidesPerDay;

public interface RidesPerDayService {
  RidesPerDay getRidesPerDay(LocalDate date);

  List<RidesPerDay> getRidesPerDayInPeriod (LocalDate startDate, LocalDate endDate);

  List<RidesPerDay> getAllRidesPerDay();

void saveRidesPerDay(RidesPerDay ridesPerDay);


}


  //RidesPerDay
    //  RidesPerPlace
//AveragePassengerCount