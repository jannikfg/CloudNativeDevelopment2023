package thi.cnd.adapter.api.rest.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserResponse {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private LocalDate birthDate;
}
