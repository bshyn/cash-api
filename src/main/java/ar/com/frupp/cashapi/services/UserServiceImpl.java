package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.exceptions.ErrorCode;
import ar.com.frupp.cashapi.exceptions.NotFoundException;
import ar.com.frupp.cashapi.models.UserModel;
import ar.com.frupp.cashapi.repositories.UserRepository;
import ar.com.frupp.cashapi.utils.UserMapper;
import ar.com.frupp.cashapi.validators.RequestValidator;
import ar.com.frupp.cashapi.validators.UserCreationRequestValidator;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.Map;
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

        return opt.orElseThrow(() -> getNotFoundException(userId));
    }

    @Override
    public User createUser(UserModel model) {
        RequestValidator validator = new UserCreationRequestValidator(model);
        validator.validate();
        return this.repository.save(
            UserMapper.toEntity(model)
        );
    }

    @Override
    public void deleteUserById(int id) {
        this.findById(id);
        this.repository.deleteById(id);
    }

    private NotFoundException getNotFoundException(int userId) {
        String errorDescription = String.format("User with ID %d not found", userId);
        Map<String, String> errors = Collections.singletonMap("id", errorDescription);

        return new NotFoundException(ErrorCode.USER_NOT_FOUND, errors);
    }
}
