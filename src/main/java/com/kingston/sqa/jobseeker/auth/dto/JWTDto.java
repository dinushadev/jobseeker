package com.kingston.sqa.jobseeker.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTDto {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    private String userName;
    private Long userId;
    private UserType role;
    private String firstName;

}
