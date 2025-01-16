package jp.co.sysystem.training.guide.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import jp.co.sysystem.training.guide.domain.dto.LoginDTO;
import jp.co.sysystem.training.guide.domain.repository.UserRepository;

@Service
@Transactional
public class LoginService {

  @Autowired
  protected UserRepository urep;

  @Autowired
  private ModelMapper modelMapper;

  public Optional<LoginDTO> checkLoginUser(String username, String password) {
    return urep.findById(username)
            .filter(user -> user.getPassword().equals(password))
            .map(user -> modelMapper.map(user, LoginDTO.class));
  }
}
