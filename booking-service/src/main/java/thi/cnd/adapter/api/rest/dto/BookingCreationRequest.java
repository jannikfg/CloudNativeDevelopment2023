package thi.cnd.adapter.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class BookingCreationRequest {
  private String user;
  private LocalDate date;
  private String rideId;
}
