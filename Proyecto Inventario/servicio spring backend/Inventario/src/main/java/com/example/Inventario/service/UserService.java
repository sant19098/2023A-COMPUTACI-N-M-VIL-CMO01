package com.example.Inventario.service;

import com.example.Inventario.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public User getUser(User user);

    public User insertIntoDatabase(User user);


}
