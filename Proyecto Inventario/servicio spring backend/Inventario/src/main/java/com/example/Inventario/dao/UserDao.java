package com.example.Inventario.dao;

import com.example.Inventario.entity.User;
import org.springframework.data.repository.CrudRepository;


public interface UserDao extends CrudRepository<User,String> {
}