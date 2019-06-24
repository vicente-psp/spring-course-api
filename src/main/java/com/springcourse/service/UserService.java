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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

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

    public int updateRole(User user) {
        return userRepository.updateRole(user.getId(), user.getRole());
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(email);

        if(!result.isPresent()) {
            throw new UsernameNotFoundException("Doesn't exist user with email: " + email);
        }

        User user = result.get();

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        org.springframework.security.core.userdetails.User userSpring =
                new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);

        return userSpring;
    }
}
