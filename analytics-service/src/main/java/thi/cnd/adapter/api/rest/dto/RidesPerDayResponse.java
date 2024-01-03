package thi.cnd.adapter.api.rest.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thi.cnd.domain.model.RidesPerDay;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RidesPerDayResponse {
  private LocalDate date;
  private int ridesPerDay;

  public static RidesPerDayResponse fromDomainObject(RidesPerDay ridesPerDay){
    return new RidesPerDayResponse(ridesPerDay.getDate(), ridesPerDay.getRidesPerDay());
  }
}
