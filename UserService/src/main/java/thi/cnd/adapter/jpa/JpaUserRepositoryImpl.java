package thi.cnd.adapter.jpa;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import thi.cnd.adapter.jpa.entities.UserEntity;
import thi.cnd.domain.model.User;
import thi.cnd.ports.outgoing.UserRepository;

@ApplicationScoped
public class JpaUserRepositoryImpl implements UserRepository {

  @Inject
  JpaUserRepository jpaUserRepository;

  @Override
  public void save(User user) {
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(user.getFirstName());
    userEntity.setLastName(user.getLastName());
    userEntity.setEmail(user.getEmail());
    userEntity.setPassword(user.getPassword());
    userEntity.setBirthDate(user.getBirthDate());
    jpaUserRepository.persist(userEntity);
  }

  @Override
  public User findById(String email) {
    Optional<UserEntity> byId = jpaUserRepository.findByIdOptional(email);
    return byId.map(UserEntity::toUser).orElse(null);
  }
}
