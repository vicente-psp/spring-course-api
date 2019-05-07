package com.springcourse.service;

import com.springcourse.domain.User;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    public User save(User user) {
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User userCreated = userRepository.save(user);
        return userCreated;
    }

    public User update(User user) {
        String hash = HashUtil.getSecureHash(user.getPassword());
        user.setPassword(hash);

        User userUpdated = userRepository.save(user);
        return userUpdated;
    }

    public User getById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.get();
    }

    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User login(String email, String password) {
        password = HashUtil.getSecureHash(password);
        Optional<User> userOptional = userRepository.login(email, password);
        return userOptional.get();
    }

}
