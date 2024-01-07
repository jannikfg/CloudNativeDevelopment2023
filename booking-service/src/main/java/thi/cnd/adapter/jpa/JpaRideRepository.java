package thi.cnd.adapter.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import thi.cnd.adapter.jpa.entities.RideEntity;

@ApplicationScoped
public class JpaRideRepository implements PanacheRepositoryBase<RideEntity, String> {
}
