package thi.cnd.adapter.api.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import thi.cnd.adapter.api.rest.dto.RideCreationRequest;
import thi.cnd.adapter.api.rest.dto.RideResponse;
import thi.cnd.domain.RideService;
import thi.cnd.domain.model.Ride;

import java.util.List;

@ApplicationScoped
@NoArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/rides")
public class RideController {

  @Inject
  RideService rideService;

  @POST
  @Path("/create")
  @Transactional
  public RideResponse createRide(@RequestBody RideCreationRequest rideCreationRequest) {
    Ride ride = rideService.createRide(rideCreationRequest.getOrigin(),
        rideCreationRequest.getDestination(), rideCreationRequest.getDate(),
        rideCreationRequest.getTime(), rideCreationRequest.getDriver(),
        rideCreationRequest.getDescription(), rideCreationRequest.getCapacity());
    return new RideResponse(ride.getId().toString(), ride.getOrigin(), ride.getDestination(),
        ride.getDate(), ride.getTime(), ride.getDriver(), ride.getDescription(),
        ride.getCapacity());
  }

  @GET
  @Path("/{id}")
  public RideResponse getRide(@PathParam("id") String id) {
    Ride ride = rideService.findRideById(id);
    return new RideResponse(ride.getId().toString(), ride.getOrigin(), ride.getDestination(),
        ride.getDate(), ride.getTime(), ride.getDriver(), ride.getDescription(),
        ride.getCapacity());
  }

  @GET
  @Path("/all")
  public List<RideResponse> getAllRides() {
    return rideService.findAll().stream().map(RideResponse::fromEntity).toList();
  }

  @GET
  @Path("/{driver}/all")
  public List<RideResponse> getRidesByDriver(@PathParam("driver") String driver) {
    List<Ride> ridesByDriver = rideService.findRidesByDriver(driver);
    return ridesByDriver.stream().map(RideResponse::fromEntity).toList();
  }

}
