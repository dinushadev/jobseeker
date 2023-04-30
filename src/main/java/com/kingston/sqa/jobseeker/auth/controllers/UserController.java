package com.kingston.sqa.jobseeker.auth.controllers;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.auth.dto.JWTDto;
import com.kingston.sqa.jobseeker.auth.dto.UserDto;
import com.kingston.sqa.jobseeker.auth.dto.UserLoginDto;
import com.kingston.sqa.jobseeker.auth.services.UserService;
import com.kingston.sqa.jobseeker.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

   @Autowired
   private UserService userService;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private JwtTokenUtil jwtTokenUtil;

   @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) throws Exception {
      authenticate(userLoginDto.getUsername(), userLoginDto.getPassword());
      final UserDetails userDetails = userService.loadUserByUsername(userLoginDto.getUsername());
      final String token = jwtTokenUtil.generateToken(userDetails);
      return ResponseEntity.ok(new JWTDto(token));
   }

   @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody UserDto userDto) {
      User user = new User();
      user.setUsername(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setEmail(userDto.getEmail());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      userService.saveUser(user);
      return ResponseEntity.ok("success");
   }

   private void authenticate(String username, String password) throws Exception {
      try {
         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      } catch (DisabledException e) {
         throw new Exception("USER_DISABLED", e);
      } catch (BadCredentialsException e) {
         throw new Exception("INVALID_CREDENTIALS", e);
      }
   }
}