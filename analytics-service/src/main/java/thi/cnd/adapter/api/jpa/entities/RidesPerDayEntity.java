package thi.cnd.adapter.api.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thi.cnd.domain.model.RidesPerDay;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RidesPerDayEntity {
  @Id
  private LocalDate date;
  private int ridesPerDay;

 public RidesPerDay toDomainModel() {
   return new RidesPerDay(date, ridesPerDay);
 }
}
