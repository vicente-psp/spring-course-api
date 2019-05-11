package com.springcourse.repository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired private UserRepository userRepository;

    @Test
    public void aSaveTest() {
        User user = new User(null, "Vicente", "vicente@hotmail.com", "123", Role.ADMINISTRATOR, null, null);
        User createdUser = userRepository.save(user);

        assertThat(createdUser.getId()).isEqualTo(1L);
    }

    @Test
    public void updateTest() {
        User user = new User(1L, "Vicente Silva", "vicente@hotmail.com", "123", Role.ADMINISTRATOR, null, null);
        User updatedUser = userRepository.save(user);

        assertThat(updatedUser.getName()).isEqualTo("Vicente Silva");
    }

    @Test
    public void getByIdTest() {
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.get();

        assertThat(user.getPassword()).isEqualTo("123");
    }

    @Test
    public void listTest() {
        List<User> userList = userRepository.findAll();

        assertThat(userList.size()).isEqualTo(1);
    }

    @Test
    public void loginTest() {
        Optional<User> optionalUser = userRepository.login("vicente@hotmail.com", "123");
        User user = optionalUser.get();

        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void updateRoleTeste() {
        int affectedRows = userRepository.updateRole(1L, Role.SIMPLE);

        assertThat(affectedRows).isEqualTo(1);
    }

}
