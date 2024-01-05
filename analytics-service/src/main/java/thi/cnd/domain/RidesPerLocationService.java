package thi.cnd.domain;

import java.util.List;
import thi.cnd.domain.model.RidesPerLocation;

public interface RidesPerLocationService {

  RidesPerLocation getRidesPerLocation(String location);

  List<RidesPerLocation> getAllRidesPerLocation();

void saveRidesPerLocation(RidesPerLocation ridesPerLocation);

}
