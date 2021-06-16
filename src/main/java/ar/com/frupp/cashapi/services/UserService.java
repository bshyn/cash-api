package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.User;
import ar.com.frupp.cashapi.models.UserModel;

public interface UserService {
    User findById(int userId);

    User createUser(UserModel model);
}
