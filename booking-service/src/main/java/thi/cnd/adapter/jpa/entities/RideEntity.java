package thi.cnd.adapter.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import thi.cnd.domain.model.Ride;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RideEntity {

  @Id
  private String id;
  private String origin;
  private String destination;
  private LocalDate date;
  private LocalTime time;
  private String driver;
  private String description;
  private int capacity;

  public Ride toRide() {
    return new Ride(UUID.fromString(this.getId()), this.getOrigin(), this.getDestination(),
        this.getDate(), this.getTime(), this.getDriver(), this.getDescription(),
        this.getCapacity());
  }
}

