package thi.cnd.adapter.api.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import thi.cnd.adapter.api.rest.dto.BookingCreationRequest;
import thi.cnd.adapter.api.rest.dto.BookingResponse;
import thi.cnd.adapter.api.rest.dto.RideResponse;
import thi.cnd.domain.BookingService;
import thi.cnd.domain.model.Booking;

import java.util.List;

@ApplicationScoped
@NoArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/api/v1/bookings")
public class BookingController {

  @Inject
  BookingService bookingService;

  @POST
  @Path("/create")
  @Transactional
  public BookingResponse createBooking(@RequestBody BookingCreationRequest bookingCreationRequest) {
    try {
      Booking booking = bookingService.createBooking(bookingCreationRequest.getUser(),
          bookingCreationRequest.getDate(), bookingCreationRequest.getRideId());
      return new BookingResponse(booking.getId().toString(), booking.getUser(), booking.getDate(),
          RideResponse.fromEntity(booking.getRide()));
    } catch (Exception e) {
      throw new InternalServerErrorException();
    }
  }

  @GET
  @Path("/{id}")
  public BookingResponse getBooking(@PathParam("id") String id) {
    Booking booking = bookingService.findBookingById(id);
    return new BookingResponse(booking.getId().toString(), booking.getUser(), booking.getDate(),
        RideResponse.fromEntity(booking.getRide()));
  }

  @GET
  @Path("/all")
  public List<BookingResponse> getAllBookings() {
    return bookingService.findAll().stream().map(BookingResponse::fromEntity).toList();
  }

  @GET
  @Path("user/{user}")
  public List<BookingResponse> getBookingsByUser(@PathParam("user") String user) {
    return bookingService.findBookingByUser(user).stream().map(BookingResponse::fromEntity)
        .toList();
  }

}
