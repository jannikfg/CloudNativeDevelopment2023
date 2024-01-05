package thi.cnd.adapter.api.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import thi.cnd.adapter.api.jpa.entities.RidesPerDayEntity;

@ApplicationScoped
public class JpaRidesPerDayRepository implements PanacheRepositoryBase<RidesPerDayEntity, LocalDate> {

}
