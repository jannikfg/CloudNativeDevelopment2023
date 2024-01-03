package thi.cnd.adapter.api.rest;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.util.List;
import lombok.NoArgsConstructor;
import thi.cnd.adapter.api.rest.dto.AveragePassengerCountResponse;
import thi.cnd.adapter.api.rest.dto.RidesPerDayResponse;
import thi.cnd.adapter.api.rest.dto.RidesPerLocationResponse;
import thi.cnd.domain.AveragePassengerCountService;
import thi.cnd.domain.RidesPerDayService;
import thi.cnd.domain.RidesPerLocationService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@ApplicationScoped
@Transactional
@Path("/api/v1/analytics")
public class AnalyticsServiceController {

  @Inject
  RidesPerDayService ridesPerDayService;

  @Inject
  RidesPerLocationService ridesPerLocationService;

  @Inject
  AveragePassengerCountService averagePassengerCountService;

  @GET
  @Path("/ridesPerDay")
  public List<RidesPerDayResponse> getAllRidesPerDay(){
    return ridesPerDayService.getAllRidesPerDay().stream().map(RidesPerDayResponse::fromDomainObject).toList();
  }

  @GET
  @Path("/ridesPerDay/{date}")
  public RidesPerDayResponse getRidesPerDay(@PathParam("date") String date){
    return RidesPerDayResponse.fromDomainObject(ridesPerDayService.getRidesPerDay(LocalDate.parse(date)));
  }

  @GET
  @Path("/ridesPerDay/{startDate}/to/{endDate}")
  public List<RidesPerDayResponse> getRidesPerDayInPeriod(@PathParam("startDate") String startDate, @PathParam("endDate") String endDate){
    return ridesPerDayService.getRidesPerDayInPeriod(LocalDate.parse(startDate), LocalDate.parse(endDate)).stream().map(RidesPerDayResponse::fromDomainObject).toList();
  }

  @GET
  @Path("/ridesPerLocation")
  public List<RidesPerLocationResponse> getAllRidesPerLocation(){
    return ridesPerLocationService.getAllRidesPerLocation().stream().map(RidesPerLocationResponse::fromDomainObject).toList();
  }

  @GET
  @Path("/ridesPerLocation/{location}")
  public RidesPerLocationResponse getRidesPerLocation(@PathParam("location") String location){
    return RidesPerLocationResponse.fromDomainObject(
        ridesPerLocationService.getRidesPerLocation(location));
  }

  @GET
  @Path("/averagePassengerCount/{date}")
  public AveragePassengerCountResponse getAveragePassengerCountPerDay(@PathParam("date") String date){
    return AveragePassengerCountResponse.fromDomainObject(averagePassengerCountService.getAveragePassengerCountPerDay(LocalDate.parse(date)));
  }

  @GET
  @Path("/averagePassengerCount")
  public List<AveragePassengerCountResponse> getAllAveragePassengerCounts(){
    return averagePassengerCountService.getAllAveragePassengerCounts().stream().map(AveragePassengerCountResponse::fromDomainObject).toList();
  }


  //RidesPerDay
  //RidesPerPlace
  //AveragePassengerCount



}
