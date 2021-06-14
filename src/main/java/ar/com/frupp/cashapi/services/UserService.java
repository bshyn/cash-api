package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.models.UserModel;

public interface UserService {
    UserModel findById(int userId);
}
