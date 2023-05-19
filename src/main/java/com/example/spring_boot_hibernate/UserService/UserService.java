package com.example.spring_boot_hibernate.UserService;

import com.example.spring_boot_hibernate.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserService {


    void addUser(User user);

    List<User> getUsers();
    void deleteUser(Long id);
    void changeDataUser(Long id, User userAfter);
    User getById(Long id);
}
