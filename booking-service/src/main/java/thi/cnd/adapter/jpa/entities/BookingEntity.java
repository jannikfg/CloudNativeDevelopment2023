package thi.cnd.adapter.jpa.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import thi.cnd.domain.model.Booking;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class BookingEntity {

  @Id
  private String id;
  private String userId;
  private LocalDate date;
  @ManyToOne(cascade = CascadeType.PERSIST)
  private RideEntity ride;

  public Booking toBooking() {
    return new Booking(UUID.fromString(this.getId()), this.getUserId(), this.getDate(),
        this.getRide().toRide());
  }

}
