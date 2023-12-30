package thi.cnd.adapter.jpa.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import thi.cnd.domain.model.User;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserEntity{
  private String firstName;
  private String lastName;
  @Id
  private String email;
  private String password;
  private Date birthDate;

  public User toUser(){
    return new User(
        this.getFirstName(),
        this.getLastName(),
        this.getEmail(),
        this.getPassword(),
        this.getBirthDate()
    );
  }
}
