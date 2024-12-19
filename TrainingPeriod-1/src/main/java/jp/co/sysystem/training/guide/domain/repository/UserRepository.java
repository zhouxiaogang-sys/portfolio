package jp.co.sysystem.training.guide.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.co.sysystem.training.guide.domain.table.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
