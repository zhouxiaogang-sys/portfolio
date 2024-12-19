package jp.co.sysystem.training.guide.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.sysystem.training.guide.domain.repository.UserRepository;
import jp.co.sysystem.training.guide.domain.table.User;
import lombok.NonNull;

@Service
@Transactional
public class LoginService {
  
  @Autowired
  protected UserRepository urep;

  public Optional<User> checkLoginUser(@NonNull String username, @NonNull String password) {
    return urep.findById(username)
            .filter(user -> user.getPassword().equals(password))
            .map(user -> { return user;
            });
  }
}
