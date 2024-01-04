package thi.cnd.adapter.jpa.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thi.cnd.domain.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserEntity {

  private String firstName;
  private String lastName;
  @Id
  private String email;
  private String password;
  private LocalDate birthDate;

  public User toUser() {
    return new User(
        this.getFirstName(),
        this.getLastName(),
        this.getEmail(),
        this.getPassword(),
        this.getBirthDate()
    );
  }
}
