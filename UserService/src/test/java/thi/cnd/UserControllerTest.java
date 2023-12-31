package thi.cnd;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UserControllerTest {

  private final String API_URL = "api/v1/users";
  @Test
  void testCreateUserEndpoint(){
    ;
  }
}
