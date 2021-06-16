package ar.com.frupp.cashapi.repositories;

import ar.com.frupp.cashapi.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findById(int id);
}
