package thi.cnd.adapter.api.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thi.cnd.domain.model.RidesPerLocation;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class RidesPerLocationEntity {
  @Id
  private String location;
  private int ridesPerLocation;

  public RidesPerLocation toDomainModel() {
    return new RidesPerLocation(location, ridesPerLocation);
  }
}
