package thi.cnd.ports.outgoing;

import jakarta.enterprise.context.ApplicationScoped;
import thi.cnd.domain.model.User;

public interface UserRepository {
  void save(User user);
  User findById(String email);
}
