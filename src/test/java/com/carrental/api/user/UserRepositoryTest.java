package com.carrental.api.user;

import com.carrental.api.entity.User;
import com.carrental.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepositoryTest;

    @Test
    void shouldSaveAndFindUserByEmail() {
        User user = new User("Theo", "theo@test.com", "hashedpassword");
        userRepositoryTest.save(user);

        Optional<User> foundUser = userRepositoryTest.findByEmail("theo@test.com");
        assertTrue(foundUser.isPresent());
    }
}
