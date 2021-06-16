package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.exceptions.NotFoundException;
import ar.com.frupp.cashapi.models.UserModel;
import ar.com.frupp.cashapi.repositories.UserRepository;
import ar.com.frupp.cashapi.utils.UserMapper;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findById(int userId) {
        Optional<User> opt = this.repository.findById(userId);

        return opt.orElseThrow(NotFoundException::new);
    }

    @Override
    public User createUser(UserModel model) {
        return this.repository.save(
            UserMapper.toEntity(model)
        );
    }
}
