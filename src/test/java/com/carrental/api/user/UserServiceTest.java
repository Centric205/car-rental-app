package com.carrental.api.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    // Tests go here

    // TEST CASE 1: Create user successfully
    @Test
    void createUser_shouldSaveUser_whenEmailDoesNotExist(){
        User user = new User("Theo Madikgetla", "theo@gmail.com", "hashedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        User savedUser = userService.registerUser(user);

        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("theo@gmail.com");

        verify(userRepository).save(user);
    }

    // TEST CASE 2: Fail if email already exists
    @Test
    void createUser_shouldThrowException_whenEmailExists(){
        User user = new User("Theo Madikgetla", "theo@gmail.com", "hashedPassword");

        when(userRepository.existsByEmail(user.getEmail()))
                .thenReturn(true);

        assertThatThrownBy(() -> userService.registerUser(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Email already exists");

        verify(userRepository, never()).save(any());
    }

    // TEST CASE 3: Get User by ID (Success)
    @Test
    void getUserById_shouldReturnUser_whenFound(){
        User user = new User("Theo Madikgetla", "theo@gmail.com", "hashedPassword");

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);
        assertThat(foundUser).isEqualTo(user);
    }

    // TEST CASE 4: Get user by ID (Not Found)
    @Test
    void getUserById_shouldThrowException_whenNotFound(){
        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUserById(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("User not found");
    }

}
