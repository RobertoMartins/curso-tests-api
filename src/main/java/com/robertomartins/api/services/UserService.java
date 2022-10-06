package com.robertomartins.api.services;

import com.robertomartins.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
