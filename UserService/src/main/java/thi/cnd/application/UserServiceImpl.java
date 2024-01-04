package thi.cnd.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.Objects;
import thi.cnd.domain.UserService;
import thi.cnd.domain.model.User;
import thi.cnd.ports.outgoing.UserRepository;

@ApplicationScoped
public class UserServiceImpl implements UserService {

  @Inject
  UserRepository userRepository;

  @Override
  public User createUser(String firstName, String lastName, String email, String password,
      LocalDate birthDate) {
    User user = User.builder().firstName(firstName).lastName(lastName).email(email)
        .password(password).birthDate(birthDate).build();
    userRepository.save(user);
    return user;
  }

  @Override
  public User findUser(String email) {
    return userRepository.findById(email);
  }

  @Override
  public Boolean verifyUser(String email, String password) {
    User user = userRepository.findById(email);

    return Objects.equals(user.getPassword(), password);
  }
}
