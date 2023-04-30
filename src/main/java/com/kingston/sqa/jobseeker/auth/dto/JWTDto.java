package com.kingston.sqa.jobseeker.auth.dto;

import lombok.Data;

@Data
public class JWTDto {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JWTDto(String jwttoken) {
        this.jwttoken = jwttoken;
    }
}
