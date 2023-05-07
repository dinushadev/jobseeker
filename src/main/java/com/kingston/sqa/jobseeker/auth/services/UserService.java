package com.kingston.sqa.jobseeker.auth.services;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.auth.dto.UserType;
import com.kingston.sqa.jobseeker.auth.repositories.UserRepository;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.respositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        //   return userOpt.orElseThrow(new UsernameNotFoundException("User not found"))
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(userOpt.get().getUsername(), userOpt.get().getPassword(), new ArrayList<>());
    }


    public User loadUserFromUsername(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return userOpt.get();
    }

    public User saveJobSeeker(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserType.JOB_SEEKER);
        User saveUser= userRepository.save(user);
        profileRepository.save(Profile.builder().id(saveUser.getId()).build());
        return  saveUser;
    }
}