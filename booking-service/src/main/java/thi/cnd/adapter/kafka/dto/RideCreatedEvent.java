package thi.cnd.adapter.kafka.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import thi.cnd.domain.model.Ride;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RideCreatedEvent {
  private UUID id;
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;

  public static RideCreatedEvent fromDomainObject(Ride ride) {
    return RideCreatedEvent.builder()
      .id(ride.getId())
      .origin(ride.getOrigin())
      .destination(ride.getDestination())
      .date(ride.getDate())
      .time(ride.getTime())
      .driver(ride.getDriver())
      .description(ride.getDescription())
      .capacity(ride.getCapacity())
      .build();
  }

}
