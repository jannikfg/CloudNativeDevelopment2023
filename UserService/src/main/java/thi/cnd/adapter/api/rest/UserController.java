package thi.cnd.adapter.api.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import thi.cnd.adapter.api.rest.dto.LoginRequest;
import thi.cnd.adapter.api.rest.dto.UserCreationRequest;
import thi.cnd.adapter.api.rest.dto.UserResponse;
import thi.cnd.domain.UserService;
import thi.cnd.domain.model.User;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@NoArgsConstructor
@ApplicationScoped
@Transactional
@Path("/api/v1/users")
public class UserController {

  @Inject
  UserService userService;
  @POST
  @Path("/create")
  public UserResponse createUser(@RequestBody UserCreationRequest userToCreate){
    User user = userService.createUser(userToCreate.getFirstName(), userToCreate.getLastName(),
        userToCreate.getEmail(), userToCreate.getPassword(), userToCreate.getBirthDate());
    return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getPassword(), user.getBirthDate());
  }

  @GET
  @Path("user")
  public UserResponse getUser(@QueryParam("email") String email){
    User user = userService.findUser(email);
    return new UserResponse(user.getFirstName(), user.getLastName(), user.getEmail(),
        user.getPassword(), user.getBirthDate());
  }

  @POST
  @Path("user/verify")
  public boolean verifyUser(@RequestBody LoginRequest loginRequest){
    return userService.verifyUser(loginRequest.getEmail(), loginRequest.getPassword());
  }
}
