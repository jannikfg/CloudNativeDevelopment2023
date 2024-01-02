package thi.cnd.domain;

import java.time.LocalDate;
import thi.cnd.domain.model.User;

public interface UserService {

  User createUser(String firstName, String lastName, String email, String password,
      LocalDate birthDate);

  User findUser(String email);

  Boolean verifyUser(String email, String password);
}
