package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import thi.cnd.domain.DataLoadService;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;
import thi.cnd.ports.ingoing.DataLoadPort;

@ApplicationScoped
public class DataLoadServiceImpl implements DataLoadService {

  @Inject
  DataLoadPort dataLoadPort;

  @Override
  public List<Booking> getAllBookings() {
    return dataLoadPort.getAllBookings();
  }

  @Override
  public List<Ride> getAllRides() {
    return dataLoadPort.getAllRides();
  }


}
