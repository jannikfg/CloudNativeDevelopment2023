package thi.cnd.adapter.api.jpa;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Date;
import thi.cnd.adapter.api.jpa.entities.RidesPerLocationEntity;

@ApplicationScoped
public class JpaRidesPerLocationRepository implements
    PanacheRepositoryBase<RidesPerLocationEntity, String> {

}
