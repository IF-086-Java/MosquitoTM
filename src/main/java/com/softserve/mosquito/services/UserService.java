package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.repositories.GenericCRUD;
import com.softserve.mosquito.repositories.UserRepo;

import java.util.List;

public class UserService implements GenericCRUD<User> {

    private GenericCRUD<User> crud = new UserRepo();
    private UserRepo userRepo = new UserRepo();

    @Override
    public User create(User user) {
        return crud.create(user);
    }

    @Override
    public User read(Long id) {
        return crud.read(id);
    }

    @Override
    public User update(User user) {
        return crud.update(user);
    }

    @Override
    public void delete(User user) {
        crud.delete(user);
    }

    @Override
    public List<User> readAll() {
        return crud.readAll();

    }

    public User getUserByEmail(String email) {
        return this.userRepo.getUserByEmail(email);
    }
}
