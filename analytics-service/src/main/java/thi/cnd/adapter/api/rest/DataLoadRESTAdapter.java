package thi.cnd.adapter.api.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.FileSystemNotFoundException;
import java.util.List;
import org.apache.http.util.EntityUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import thi.cnd.domain.model.inputObjects.Booking;
import thi.cnd.domain.model.inputObjects.Ride;
import thi.cnd.ports.ingoing.DataLoadPort;

@ApplicationScoped
public class DataLoadRESTAdapter implements DataLoadPort {

  @ConfigProperty(name = "BOOKING_SERVICE_RIDES_BASE_URL")
  String BOOKING_SERVICE_RIDES_BASE_URL;

  @ConfigProperty(name = "BOOKING_SERVICE_BOOKINGS_BASE_URL")
  String BOOKING_SERVICE_BOOKINGS_BASE_URL;

  public static void main(String[] args) {
    DataLoadRESTAdapter dataLoadRESTAdapter = new DataLoadRESTAdapter();
  }

  private Ride[] convertJsonToRide(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.readValue(json, Ride[].class);
  }

  private Booking[] convertJsonToBooking(String json) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.readValue(json, Booking[].class);
  }

  private static HttpRequest defineHttpRequest(String baseUrl, String path) {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(baseUrl + path))
        .method("GET", HttpRequest.BodyPublishers.noBody())
        .build();

    return request;
  }

  private static HttpResponse<String> sendRequest(HttpRequest request) {
    HttpResponse<String> response = null;
    try {
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return response;
  }

  @Override
  public List<Booking> getAllBookings() {
    HttpRequest request = defineHttpRequest(BOOKING_SERVICE_BOOKINGS_BASE_URL, "/all");
    HttpResponse<String> response = sendRequest(request);
    System.out.println(response.body());
    try {
      return List.of(convertJsonToBooking(response.body()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Ride> getAllRides() {
    HttpRequest request = defineHttpRequest(BOOKING_SERVICE_RIDES_BASE_URL, "/all");
    System.out.println(request);
    HttpResponse<String> response = sendRequest(request);
    System.out.println(response.body());

    try {
      return List.of(convertJsonToRide(response.body()));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
