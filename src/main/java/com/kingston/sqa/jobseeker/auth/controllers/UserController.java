package com.kingston.sqa.jobseeker.auth.controllers;

import com.kingston.sqa.jobseeker.api.response.BaseResponse;
import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.auth.dto.JWTDto;
import com.kingston.sqa.jobseeker.auth.dto.UserDto;
import com.kingston.sqa.jobseeker.auth.dto.UserLoginDto;
import com.kingston.sqa.jobseeker.auth.dto.UserType;
import com.kingston.sqa.jobseeker.auth.services.UserService;
import com.kingston.sqa.jobseeker.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
      final User userDetails = userService.loadUserFromUsername(userLoginDto.getUsername());

      final String token = jwtTokenUtil.generateToken(userDetails);
      BaseResponse<JWTDto> res = new BaseResponse<>();
      res.setData(new JWTDto(token, userDetails.getUsername(), userDetails.getId(), userDetails.getRole(), userDetails.getFirstName()));
      return ResponseEntity.ok(res);
   }

   @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody UserDto userDto) {
      User user = new User();
      user.setUsername(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setEmail(userDto.getEmail());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      BaseResponse<Object> res = new BaseResponse<>();

      User savedUser;
      try{

         savedUser = userService.saveJobSeeker(user);
         res.setSuccess(true);
      }catch (Exception e){
         res.setSuccess(false);
         res.setErrorCode("DUPLICATE_USER");
      }

      return ResponseEntity.ok(res);
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