package thi.cnd.adapter.api.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import thi.cnd.domain.model.Booking;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookingResponse {
  private String id;
  private String user;
  private LocalDate date;
  private RideResponse ride;

  public static BookingResponse fromEntity(Booking booking) {
    return new BookingResponse(booking.getId().toString(), booking.getUser(), booking.getDate(),
        RideResponse.fromEntity(booking.getRide()));
  }

}
