package com.carrental.api.user;

import java.util.Optional;

public interface UserService {

    User registerUser(User user);

    User getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    boolean existsByEmail(String email);
}
