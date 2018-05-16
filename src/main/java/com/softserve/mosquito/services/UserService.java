package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.repositories.GenericCRUD;
import com.softserve.mosquito.repositories.UserRepo;

import java.util.List;

public class UserService  implements GenericCRUD<User> {

    UserRepo userRepo = new UserRepo();

    @Override
    public User create(User user) {
        return userRepo.create(user);
    }

    @Override
    public User read(Long id) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(User user) {

    }

    @Override
    public List<User> readAll() {
        return null;
    }
}
