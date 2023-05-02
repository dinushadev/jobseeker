package com.kingston.sqa.jobseeker.auth.services;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.auth.dto.UserType;
import com.kingston.sqa.jobseeker.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }
      return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
   }


   public User loadUserFromUsername(String username) {
      User user = userRepository.findByUsername(username);
      if (user == null) {
         throw new UsernameNotFoundException("User not found");
      }
      return user;
   }
   public User saveJobSeeker(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(UserType.JOB_SEEKER);
      return userRepository.save(user);
   }
}