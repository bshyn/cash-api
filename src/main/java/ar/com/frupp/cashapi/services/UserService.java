package ar.com.frupp.cashapi.services;

import ar.com.frupp.cashapi.entities.User;

public interface UserService {
    User findById(int userId);
}
