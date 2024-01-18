package thi.cnd.adapter.api.rest.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thi.cnd.domain.model.AveragePassengerCount;
import thi.cnd.domain.model.RidesPerLocation;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AveragePassengerCountResponse {
  private LocalDate date;
  private double averagePassengerCount;

  public static AveragePassengerCountResponse fromDomainObject(
      AveragePassengerCount averagePassengerCount){
    return new AveragePassengerCountResponse(averagePassengerCount.getDate(), averagePassengerCount.getAveragePassengerCount());
  }
}
