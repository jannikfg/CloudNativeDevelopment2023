package thi.cnd.adapter.api.rest.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCreationRequest {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private LocalDate birthDate;
}
