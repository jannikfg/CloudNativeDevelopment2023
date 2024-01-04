package thi.cnd.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class Ride {

  @Setter(AccessLevel.NONE)
  private UUID id;
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;

  public Ride() {
    this.id = UUID.randomUUID();
  }

}
