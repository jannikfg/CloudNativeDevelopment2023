package thi.cnd.domain.model.inputObjects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonDeserialize
public class Ride {
  private String id;
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;
}
