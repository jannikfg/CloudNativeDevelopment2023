package thi.cnd.adapter.api.rest;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import thi.cnd.domain.RidesPerDayService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@ApplicationScoped
@Transactional
@Path("/api/v1/analytics")
public class AnalyticsServiceController {

  @Inject
  RidesPerDayService ridesPerDayService;

  @GET
  @Path

  //RidesPerDay
  //RidesPerPlace
  //AveragePassengerCount



}
