package com.kingston.sqa.jobseeker.api.error;

public class InvalidLoginException extends JobSeekerException{

    public InvalidLoginException() {
        this.code = "INVALID_LOGIN_CREDENTIALS";
    }
}

