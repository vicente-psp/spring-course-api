package com.springcourse.service;

import com.springcourse.domain.User;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.repository.UserRepository;
import com.springcourse.service.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
//import java.awt.print.Pageable;
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
        return userOptional.orElseThrow(() -> new NotFoundException("Não existe usuário para o id " + id));
    }

    public List<User> listAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public PageModel<User> listAllOnLazyMode(PageRequestModel pageRequestModel) {
        Pageable pageable = PageRequest.of(pageRequestModel.getPage(), pageRequestModel.getSize());
        Page<User> userPage = userRepository.findAll(pageable);

        PageModel<User> userPageModel = new PageModel<>((int) userPage.getTotalElements(), userPage.getSize(), userPage.getTotalPages(), userPage.getContent());

        return userPageModel;
    }

    public User login(String email, String password) {
        password = HashUtil.getSecureHash(password);
        Optional<User> userOptional = userRepository.login(email, password);
        return userOptional.get();
    }

}
