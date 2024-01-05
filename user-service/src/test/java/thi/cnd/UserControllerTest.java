package thi.cnd;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import thi.cnd.adapter.api.rest.dto.LoginRequest;
import thi.cnd.adapter.api.rest.dto.UserCreationRequest;
import thi.cnd.adapter.api.rest.dto.VerificationResponse;
import thi.cnd.domain.UserService;
import thi.cnd.domain.model.User;

@QuarkusTest
public class UserControllerTest {

  private final String API_URL = "api/v1/users/";
  private final String email = "max@mustermann.de";
  private final String password = "lisaMustermann";
  private final UserCreationRequest userCreationRequest = createUserCreationRequest();
  String expectedResponse_1 = TestUtil.loadTestResource(
      "newUserCreation.json");

  @InjectMock
  UserService userService;

  private UserCreationRequest createUserCreationRequest() {
    return UserCreationRequest.builder()
        .firstName("Max")
        .lastName("Mustermann")
        .birthDate(LocalDate.of(2000, 1, 8))
        .email(email)
        .password(password)
        .build();

  }

  private User createUser() {
    return User.builder()
        .firstName("Max")
        .lastName("Mustermann")
        .email(email)
        .password(password)
        .birthDate(LocalDate.of(2000, 1, 8))
        .build();
  }

  private LoginRequest createLoginRequest() {
    return LoginRequest.builder()
        .email(email)
        .password(password)
        .build();
  }

  @Test
  void testCreateUserEndpoint() {

    when(userService.createUser(userCreationRequest.getFirstName(),
        userCreationRequest.getLastName(),
        userCreationRequest.getEmail(),
        userCreationRequest.getPassword(),
        userCreationRequest.getBirthDate())
    ).thenReturn(createUser());

    given()
        .when()
        .body(userCreationRequest)
        .contentType("application/json")
        .accept("application/json")
        .post(API_URL + "create")
        .then()
        .statusCode(200)
        .body(TestUtil.createJsonMatcher(expectedResponse_1));
  }

  @Test
  void testGetUser() {
    User user = createUser();
    when(userService.findUser(email)).thenReturn(user);
    given()
        .when()
        .get(API_URL + "user/" + email)
        .then()
        .statusCode(200)
        .body(TestUtil.createJsonMatcher(expectedResponse_1));
  }

  @Test
  void testVerifyUser() {
    User user = createUser();
    VerificationResponse verificationResponse = new VerificationResponse(email, true);
    when(userService.verifyUser(user.getEmail(), user.getPassword())).thenReturn(
        verificationResponse.getVerified());
    given()
        .when()
        .body(createLoginRequest())
        .contentType("application/json")
        .accept("application/json")
        .post(API_URL + "user/verify")
        .then()
        .statusCode(200)
        .body("verified", equalTo(verificationResponse.getVerified()));
  }
}
