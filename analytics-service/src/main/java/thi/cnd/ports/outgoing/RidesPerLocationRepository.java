package thi.cnd.ports.outgoing;

import thi.cnd.domain.model.RidesPerLocation;

public interface RidesPerLocationRepository {
  void save(RidesPerLocation ridesPerLocation);
  RidesPerLocation getRidesPerLocation(String location);

  void deleteRidesPerLocation(String location);
}
