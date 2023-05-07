package com.kingston.sqa.jobseeker.auth.services;

import com.kingston.sqa.jobseeker.auth.dto.UserType;
import com.kingston.sqa.jobseeker.auth.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;
import com.kingston.sqa.jobseeker.auth.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    /* unit test for  the loadUserByUsername method */

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Test
    void testCorrectDataLoadUserByUsername() {
        User user1 = User.builder()
                .id(1L)
                .username("dns@gmail.com")
                .password("testPass")
                .role(UserType.JOB_SEEKER)
                .build();
   /*           User user2 = User.builder()
                .id(1L)
                .username("dns2@gmail.com")
                .role(UserType.JOB_SEEKER)
                .build();
              User user3 = User.builder()
                .id(1L)
                .username("dns3@gmail.com")
                .role(UserType.JOB_SEEKER)
                .build();
              List<User> userList = List.of(user1, user2, user3);
*/
              when(userRepository.findByUsername(user1.getUsername())).thenReturn(Optional.of(user1));

              UserDetails userDetails= userService.loadUserByUsername(user1.getUsername());
              assertNotNull(userDetails);
              Assertions.assertThat(userDetails.getUsername()).isEqualTo(user1.getUsername());

    }
    @Test
    void testIncorrectUserDataLoadUserByUsername() {

        when(userRepository.findByUsername("invalid@email.com")).thenReturn(Optional.empty());
        UsernameNotFoundException thrown = assertThrows(
        UsernameNotFoundException.class , () -> userService.loadUserByUsername("invalid@email.com"),
                "Invaild username"
        );
    }

    @Test
    void testCorrectDataLoadUserFromUsername() {
        User user1 = User.builder()
                .id(1L)
                .username("dns@gmail.com")
                .password("testPass")
                .role(UserType.JOB_SEEKER)
                .build();
        when(userRepository.findByUsername(user1.getUsername())).thenReturn(Optional.of(user1));

        UserDetails userDetails= userService.loadUserByUsername(user1.getUsername());
        assertNotNull(userDetails);
        Assertions.assertThat(userDetails.getUsername()).isEqualTo(user1.getUsername());
    }

    @Test
    void testInvalidUserLoadUserFromUsername() {
        when(userRepository.findByUsername("invalid@email.com")).thenReturn(Optional.empty());
        UsernameNotFoundException thrown = assertThrows(
                UsernameNotFoundException.class , () -> userService.loadUserByUsername("invalid@email.com"),
                "Invalid username"
        );
    }
    @Test
    void testSuccessSaveJobSeeker() {
        User user1 = User.builder()
                .id(1L)
                .username("dns@gmail.com")
                .password("testPass")
                .role(UserType.JOB_SEEKER)
                .build();
        when(userRepository.save(user1)).thenReturn(user1);
        User saveUser = userService.saveJobSeeker(user1);
        assertNotNull(saveUser);
        Assertions.assertThat(saveUser.getUsername()).isEqualTo(user1.getUsername());
    }
}