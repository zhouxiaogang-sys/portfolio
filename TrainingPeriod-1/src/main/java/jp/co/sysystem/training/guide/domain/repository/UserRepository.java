package jp.co.sysystem.training.guide.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
