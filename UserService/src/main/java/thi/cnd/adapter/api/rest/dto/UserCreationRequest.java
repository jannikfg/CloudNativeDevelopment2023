package thi.cnd.adapter.api.rest.dto;

import java.util.Date;
import lombok.Data;

@Data
public class UserCreationRequest {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Date birthDate;
}
