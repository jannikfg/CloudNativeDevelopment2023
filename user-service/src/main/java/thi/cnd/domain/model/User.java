package thi.cnd.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private LocalDate birthDate;

}
