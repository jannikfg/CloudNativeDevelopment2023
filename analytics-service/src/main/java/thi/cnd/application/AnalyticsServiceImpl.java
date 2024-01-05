package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.awt.print.Book;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import thi.cnd.domain.AveragePassengerCountService;
import thi.cnd.domain.DataLoadService;
import thi.cnd.domain.RidesPerDayService;
import thi.cnd.domain.RidesPerLocationService;
import thi.cnd.domain.model.AveragePassengerCount;
import thi.cnd.domain.model.RidesPerDay;
import thi.cnd.domain.model.RidesPerLocation;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;
import thi.cnd.ports.outgoing.AveragePassengerCountRepository;
import thi.cnd.ports.outgoing.RidesPerDayRepository;
import thi.cnd.ports.outgoing.RidesPerLocationRepository;

@ApplicationScoped
public class AnalyticsServiceImpl implements RidesPerDayService, RidesPerLocationService,
    AveragePassengerCountService {

  @Inject
  DataLoadService dataLoadService;

  @Inject
  AveragePassengerCountRepository averagePassengerCountRepository;

  @Inject
  RidesPerLocationRepository ridesPerLocationRepository;

  @Inject
  RidesPerDayRepository ridesPerDayRepository;

  @Override
  public RidesPerDay getRidesPerDay(LocalDate date) {
    int ridesPerDay = 0;
    List<Ride> rides = dataLoadService.getAllRides();
    ridesPerDay = rides.stream().filter(ride -> ride.getDate().equals(date)).toList().size();
    RidesPerDay ridesPerDayObject = RidesPerDay.builder()
        .date(date)
        .ridesPerDay(ridesPerDay)
        .build();
    saveRidesPerDay(ridesPerDayObject);
    return ridesPerDayObject;
  }

  @Override
  public List<RidesPerDay> getRidesPerDayInPeriod(LocalDate startDate, LocalDate endDate) {
    List<Ride> rides = dataLoadService.getAllRides();

    rides = rides.stream().filter(ride -> ride.getDate().isAfter(startDate) && ride.getDate().isBefore(endDate)).toList();
    List<RidesPerDay> ridesPerDayList = new ArrayList<>();
    ridesPerDayList = getRidesPerDays(rides);
    for (RidesPerDay ridesPerDay : ridesPerDayList) {
      saveRidesPerDay(ridesPerDay);
    }
    return ridesPerDayList;
  }

  @Override
  public List<RidesPerDay> getAllRidesPerDay() {
    List<Ride> rides = dataLoadService.getAllRides();
    List<RidesPerDay> ridesPerDayList = new ArrayList<>();
    ridesPerDayList = getRidesPerDays(rides);
    for (RidesPerDay ridesPerDay : ridesPerDayList) {
      saveRidesPerDay(ridesPerDay);
    }
    return ridesPerDayList;
  }

  @Override
  public void saveRidesPerDay(RidesPerDay ridesPerDay) {
    RidesPerDay existingRidesPerDay = ridesPerDayRepository.getRidesPerDay(ridesPerDay.getDate());

    if (existingRidesPerDay == null) {
      ridesPerDayRepository.save(ridesPerDay);
    } else {
      if (!existingRidesPerDay.equals(ridesPerDay)) {
        existingRidesPerDay.setRidesPerDay(ridesPerDay.getRidesPerDay());
        ridesPerDayRepository.save(existingRidesPerDay);
      }
    }
  }

  @Override
  public RidesPerLocation getRidesPerLocation(String location) {
    int ridesPerLocation = 0;
    List<Ride> rides = dataLoadService.getAllRides();
    ridesPerLocation = rides.stream().filter(ride -> ride.getOrigin().equals(location)).toList().size();
    RidesPerLocation ridesPerLocationObject = RidesPerLocation.builder()
        .location(location)
        .ridesPerLocation(ridesPerLocation)
        .build();
    saveRidesPerLocation(ridesPerLocationObject);
    return ridesPerLocationObject;
  }

  @Override
  public List<RidesPerLocation> getAllRidesPerLocation() {
    List<Ride> rides = dataLoadService.getAllRides();
    List<RidesPerLocation> ridesPerLocationList = new ArrayList<>();

    Map<String, Integer> ridesMap = new HashMap<>();

    for (Ride ride : rides) {
      String location = ride.getOrigin();
      ridesMap.put(location, ridesMap.getOrDefault(location, 0) + 1);
    }

    for (Entry<String, Integer> entry : ridesMap.entrySet()) {
      RidesPerLocation ridesPerLocation = new RidesPerLocation();
      ridesPerLocation.setLocation(entry.getKey());
      ridesPerLocation.setRidesPerLocation(entry.getValue());
      ridesPerLocationList.add(ridesPerLocation);
    }
    for (RidesPerLocation ridesPerLocation : ridesPerLocationList) {
      saveRidesPerLocation(ridesPerLocation);
    }
    return ridesPerLocationList;
  }

  @Override
  public void saveRidesPerLocation(RidesPerLocation ridesPerLocation) {
    RidesPerLocation existingRidesPerLocation = ridesPerLocationRepository.getRidesPerLocation(ridesPerLocation.getLocation());

    if (existingRidesPerLocation == null) {
      ridesPerLocationRepository.save(ridesPerLocation);
    } else {
      if (!existingRidesPerLocation.equals(ridesPerLocation)) {
        existingRidesPerLocation.setRidesPerLocation(ridesPerLocation.getRidesPerLocation());
        ridesPerLocationRepository.save(existingRidesPerLocation);
      }
    }
  }

  @Override
  public AveragePassengerCount getAveragePassengerCountPerDay(LocalDate date) {
    int averagePassengerCount = 0;

    List<Ride> rides = dataLoadService.getAllRides();
    List<Booking> bookings = dataLoadService.getAllBookings();
    int passengerCount = 0;

    rides = rides.stream().filter(ride -> ride.getDate().equals(date)).toList();

    for (Ride ride : rides) {
      passengerCount += getPassengerCountPerRide(ride, bookings);
    }

    averagePassengerCount = passengerCount / rides.size();
    AveragePassengerCount averagePassengerCountObject = AveragePassengerCount.builder()
        .date(date)
        .averagePassengerCount(averagePassengerCount)
        .build();

    saveAveragePassengerCount(averagePassengerCountObject);
    return averagePassengerCountObject;
  }

  @Override
  public List<AveragePassengerCount> getAllAveragePassengerCounts() {
    List<Ride> rides = dataLoadService.getAllRides();
    List<Booking> bookings = dataLoadService.getAllBookings();
    List<AveragePassengerCount> averagePassengerCountList = new ArrayList<>();

    Map<LocalDate, Integer> averagePassengerCountMap = new HashMap<>();

    for (Ride ride : rides) {
      LocalDate date = ride.getDate();
      averagePassengerCountMap.put(date, averagePassengerCountMap.getOrDefault(date, 0) + getPassengerCountPerRide(ride, bookings));
    }

    for (Entry<LocalDate, Integer> entry : averagePassengerCountMap.entrySet()) {
      AveragePassengerCount averagePassengerCount = new AveragePassengerCount();
      averagePassengerCount.setDate(entry.getKey());
      averagePassengerCount.setAveragePassengerCount(entry.getValue() / rides.size());
      averagePassengerCountList.add(averagePassengerCount);
    }
    for (AveragePassengerCount averagePassengerCount : averagePassengerCountList) {
      saveAveragePassengerCount(averagePassengerCount);
    }

    return averagePassengerCountList;

  }

  @Override
  public void saveAveragePassengerCount(AveragePassengerCount averagePassengerCount) {
    AveragePassengerCount existingAveragePassengerCount = averagePassengerCountRepository.getAveragePassengerCount(averagePassengerCount.getDate());

    if (existingAveragePassengerCount == null) {
      averagePassengerCountRepository.save(averagePassengerCount);
    } else {
      if (!existingAveragePassengerCount.equals(averagePassengerCount)) {
        existingAveragePassengerCount.setAveragePassengerCount(averagePassengerCount.getAveragePassengerCount());
        averagePassengerCountRepository.save(existingAveragePassengerCount);
      }
    }
  }


  public int getPassengerCountPerRide(Ride ride, List<Booking> bookings) {
    int passengerCount = 0;
    for (Booking booking : bookings) {
      if (booking.getRide().getId().equals(ride.getId())) {
        passengerCount++;
      }
    }
    return passengerCount;
  }

  public List<RidesPerDay> getRidesPerDays(List<Ride> rides) {
    Map<LocalDate, Integer> ridesMap = new HashMap<>();
    List<RidesPerDay> ridesPerDayList = new ArrayList<>();

    for (Ride ride : rides) {
      LocalDate date = ride.getDate();
      ridesMap.put(date, ridesMap.getOrDefault(date, 0) + 1);
    }

    for (Entry<LocalDate, Integer> entry : ridesMap.entrySet()) {
      RidesPerDay ridesPerDay = new RidesPerDay();
      ridesPerDay.setDate(entry.getKey());
      ridesPerDay.setRidesPerDay(entry.getValue());
      ridesPerDayList.add(ridesPerDay);
    }

    return ridesPerDayList;
  }

}


