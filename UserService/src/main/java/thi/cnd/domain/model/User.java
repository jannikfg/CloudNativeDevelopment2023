package thi.cnd.domain.model;

import io.quarkus.arc.All;
import jakarta.resource.spi.security.PasswordCredential;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private Date birthDate;
}
