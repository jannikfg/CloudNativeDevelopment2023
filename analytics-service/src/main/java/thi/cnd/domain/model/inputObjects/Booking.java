package thi.cnd.domain.model.inputObjects;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
  private String id;
  private String user;
  private LocalDate date;
  private Ride ride;
}
