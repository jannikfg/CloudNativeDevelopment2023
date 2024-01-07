package thi.cnd.adapter.api.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.Date;
import thi.cnd.adapter.api.jpa.entities.AveragePassengerCountEntity;

@ApplicationScoped
public class JpaAveragePassengerCountRepository implements PanacheRepositoryBase<AveragePassengerCountEntity, LocalDate> {

}
