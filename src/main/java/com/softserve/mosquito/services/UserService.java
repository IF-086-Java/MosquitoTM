package com.softserve.mosquito.services;

import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.repositories.UserRepo;

public class UserService{

    private UserRepo userRepo = new UserRepo();

    public User getUserByEmail(String email) {
    	return this.userRepo.getUserByEmail(email);
    }
}
