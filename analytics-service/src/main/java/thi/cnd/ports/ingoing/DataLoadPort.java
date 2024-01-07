package thi.cnd.ports.ingoing;

import java.util.List;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;

public interface DataLoadPort {
  List<Booking> getAllBookings();

  List<Ride> getAllRides();
}
