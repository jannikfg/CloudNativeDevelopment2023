package thi.cnd.adapter.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import thi.cnd.domain.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideResponse {
  private String id;
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;

  public static RideResponse fromEntity(Ride ride) {
    return new RideResponse(ride.getId().toString(), ride.getOrigin(), ride.getDestination(),
        ride.getDate(), ride.getTime(), ride.getDriver(), ride.getDescription(),
        ride.getCapacity());
  }

}
