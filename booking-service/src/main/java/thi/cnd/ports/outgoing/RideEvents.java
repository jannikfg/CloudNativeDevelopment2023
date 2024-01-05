package thi.cnd.ports.outgoing;

import thi.cnd.domain.model.Ride;

public interface RideEvents {
  void publishRideCreatedEvent(Ride ride);
}
