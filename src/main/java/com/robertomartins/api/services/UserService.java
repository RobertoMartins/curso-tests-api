package com.robertomartins.api.services;

import com.robertomartins.api.domain.User;
import com.robertomartins.api.domain.dto.UserDTO;
import org.apache.catalina.UserDatabase;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO obj);

    User update(UserDTO obj);

    void delete(Integer id);

}