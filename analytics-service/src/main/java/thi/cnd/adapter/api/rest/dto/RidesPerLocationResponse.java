package thi.cnd.adapter.api.rest.dto;

import io.quarkus.arc.All;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thi.cnd.domain.model.RidesPerDay;
import thi.cnd.domain.model.RidesPerLocation;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RidesPerLocationResponse {

  private String location;
  private int ridesPerLocation;

  public static RidesPerLocationResponse fromDomainObject(RidesPerLocation ridesPerLocation){
    return new RidesPerLocationResponse(ridesPerLocation.getLocation(), ridesPerLocation.getRidesPerLocation());
  }

}
