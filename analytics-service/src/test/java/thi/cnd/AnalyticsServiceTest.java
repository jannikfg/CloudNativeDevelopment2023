package thi.cnd;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import thi.cnd.application.AnalyticsServiceImpl;
import thi.cnd.domain.DataLoadService;
import thi.cnd.domain.model.RidesPerDay;
import thi.cnd.domain.model.RidesPerLocation;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;
import thi.cnd.ports.outgoing.RidesPerDayRepository;
import thi.cnd.ports.outgoing.RidesPerLocationRepository;

@QuarkusTest
public class AnalyticsServiceTest {

  @Inject
  AnalyticsServiceImpl analyticsService;

  @InjectMock
  RidesPerDayRepository ridesPerDayRepository;

  @InjectMock
  RidesPerLocationRepository ridesPerLocationRepository;

  @InjectMock
  DataLoadService dataLoadService;

  @Test
  public void testGetAllRidesPerDay(){
    List<Ride> rides = generateRides();
    System.out.println(rides);
    when(dataLoadService.getAllRides()).thenReturn(rides);
    doNothing().when(ridesPerDayRepository).save(any(RidesPerDay.class));
    //doNothing().when(analyticsService).saveRidesPerDay(any(RidesPerDay.class));
    List<RidesPerDay> ridesPerDay = analyticsService.getAllRidesPerDay();
    assertEquals(4, ridesPerDay.size());
    assertEquals(3, ridesPerDay.get(0).getRidesPerDay());
    assertEquals(3, ridesPerDay.get(1).getRidesPerDay());
    assertEquals(2, ridesPerDay.get(2).getRidesPerDay());
    assertEquals(2, ridesPerDay.get(3).getRidesPerDay());
  }

  @Test
  public void textGetRidesPerLocation(){
    List<Ride> rides = generateRides();
    when(dataLoadService.getAllRides()).thenReturn(rides);
    doNothing().when(ridesPerLocationRepository).save(any(RidesPerLocation.class));
    RidesPerLocation ridesPerLocationA = analyticsService.getRidesPerLocation("A");
    assertEquals(5, ridesPerLocationA.getRidesPerLocation());
    RidesPerLocation ridesPerLocationC = analyticsService.getRidesPerLocation("C");
    assertEquals(3, ridesPerLocationC.getRidesPerLocation());
    RidesPerLocation ridesPerLocationD = analyticsService.getRidesPerLocation("D");
    assertEquals(2, ridesPerLocationD.getRidesPerLocation());
  }

  @Test
  public void testGetRidesPerDay(){
    List<Ride> rides = generateRides();
    when(dataLoadService.getAllRides()).thenReturn(rides);
    RidesPerDay ridesPerDay = analyticsService.getRidesPerDay(LocalDate.of(2021, 1, 1));
    assertEquals(3, ridesPerDay.getRidesPerDay());

  }

  @Test
  public void testGetRidesPerDays() {
    List<Ride> rides = generateRides();
    List<Booking> bookings = generaterBookings();
    List<RidesPerDay> ridesPerDay = analyticsService.getRidesPerDays(rides);
    assertEquals(4, ridesPerDay.size());
    assertEquals(3, ridesPerDay.get(0).getRidesPerDay());
    assertEquals(3, ridesPerDay.get(1).getRidesPerDay());
    assertEquals(2, ridesPerDay.get(2).getRidesPerDay());
    assertEquals(2, ridesPerDay.get(3).getRidesPerDay());
  }

  @Test
  public void testGetPassengerCountPerRide(){
    List<Ride> rides = generateRides();
    List<Booking> bookings = generaterBookings();
    int passengerCountPerRide0 = analyticsService.getPassengerCountPerRide(rides.get(0), bookings);
    assertEquals(0, passengerCountPerRide0);
    int passengerCountPerRide1 = analyticsService.getPassengerCountPerRide(rides.get(1), bookings);
    assertEquals(3, passengerCountPerRide1);
    int passengerCountPerRide2 = analyticsService.getPassengerCountPerRide(rides.get(2), bookings);
    assertEquals(0, passengerCountPerRide2);
    int passengerCountPerRide4 = analyticsService.getPassengerCountPerRide(rides.get(4), bookings);
    assertEquals(3, passengerCountPerRide4);
    int passengerCountPerRide6 = analyticsService.getPassengerCountPerRide(rides.get(6), bookings);
    assertEquals(4, passengerCountPerRide6);
    int passengerCountPerRide9 = analyticsService.getPassengerCountPerRide(rides.get(9), bookings);
    assertEquals(2, passengerCountPerRide9);
  }

  private List<Ride> generateRides() {
    return List.of(
        Ride.builder().id("1")
            .origin("A")
            .destination("B")
            .date(LocalDate.of(2021, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("2")
            .origin("A")
            .destination("B")
            .date(LocalDate.of(2021, 1, 1))
            .time(LocalTime.of(12, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("3")
            .origin("A")
            .destination("B")
            .date(LocalDate.of(2021, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("4")
            .origin("A")
            .destination("B")
            .date(LocalDate.of(2022, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("5")
            .origin("A")
            .destination("B")
            .date(LocalDate.of(2022, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("6")
            .origin("C")
            .destination("B")
            .date(LocalDate.of(2022, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("7")
            .origin("C")
            .destination("B")
            .date(LocalDate.of(2023, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("8")
            .origin("C")
            .destination("B")
            .date(LocalDate.of(2023, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("9")
            .origin("D")
            .destination("B")
            .date(LocalDate.of(2024, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build(),
        Ride.builder().id("10")
            .origin("D")
            .destination("B")
            .date(LocalDate.of(2024, 1, 1))
            .time(LocalTime.of(10, 0))
            .driver("Driver 1")
            .description("Description 1")
            .capacity(4)
            .build()
    );
  }

  private List<Booking> generaterBookings(){
    return List.of(
        Booking.builder()
            .id("1")
            .user("User 1")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("2").build()).build(),
        Booking.builder()
            .id("2")
            .user("User 2")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("2").build()).build(),
        Booking.builder()
            .id("3")
            .user("User 3")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("2").build()).build(),
        Booking.builder()
            .id("4")
            .user("User 4")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("5").build()).build(),
        Booking.builder()
            .id("5")
            .user("User 5")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("5").build()).build(),
        Booking.builder()
            .id("6")
            .user("User 6")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("5").build()).build(),
        Booking.builder()
            .id("7")
            .user("User 7")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("7").build()).build(),
        Booking.builder()
            .id("8")
            .user("User 8")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("7").build()).build(),
        Booking.builder()
            .id("9")
            .user("User 9")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("7").build()).build(),
        Booking.builder()
            .id("10")
            .user("User 10")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("7").build()).build(),
        Booking.builder()
            .id("11")
            .user("User 11")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("10").build()).build(),
        Booking.builder()
            .id("12")
            .user("User 12")
            .date(LocalDate.of(2020, 1, 1))
            .ride(Ride.builder().id("10").build()).build())
        ;}

}

