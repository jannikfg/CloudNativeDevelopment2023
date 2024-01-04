package thi.cnd.adapter.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RideCreationRequest {
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;
}
