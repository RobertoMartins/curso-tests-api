package com.robertomartins.api.services;

import com.robertomartins.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
}