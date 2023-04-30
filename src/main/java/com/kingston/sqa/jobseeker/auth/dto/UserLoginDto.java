package com.kingston.sqa.jobseeker.auth.dto;

import lombok.Data;

@Data
public class UserLoginDto {

    private String firstName;
    private String lastName;
    private  String username;
    private  String password;
    private String email;
}
