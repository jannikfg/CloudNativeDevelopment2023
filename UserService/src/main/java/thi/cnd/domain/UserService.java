package thi.cnd.domain;

import java.util.Date;
import thi.cnd.domain.model.User;

public interface UserService {
  User createUser(String firstName, String lastName, String email, String password, Date birthDate);
  User findUser(String email);
}
