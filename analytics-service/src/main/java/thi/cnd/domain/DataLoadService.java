package thi.cnd.domain;

import java.util.List;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;

public interface DataLoadService {
  List<Booking> getAllBookings();
  List<Ride> getAllRides();
}
