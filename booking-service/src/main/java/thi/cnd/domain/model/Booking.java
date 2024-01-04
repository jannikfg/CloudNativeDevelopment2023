package thi.cnd.domain.model;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Data
@Builder
public class Booking {

  @Setter(AccessLevel.NONE)
  private UUID id;
  private String user;
  private LocalDate date;
  private Ride ride;

  public Booking() {
    this.id = UUID.randomUUID();
  }

}
