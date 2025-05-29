package cc.chensoul.user;

public interface UserRepository {
  User findByUsername(String username);

  User save(User user);

  User findByEmail(String email);
}
