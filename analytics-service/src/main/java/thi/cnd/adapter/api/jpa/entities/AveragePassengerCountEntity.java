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
import thi.cnd.domain.model.AveragePassengerCount;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AveragePassengerCountEntity {
  @Id
  private LocalDate date;
  private double averagePassengerCount;

  public AveragePassengerCount toDomainModel() {
    return new AveragePassengerCount(date, averagePassengerCount);
  }
}
