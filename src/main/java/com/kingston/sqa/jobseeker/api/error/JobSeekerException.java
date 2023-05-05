package com.kingston.sqa.jobseeker.api.error;

import lombok.Getter;

@Getter
public class JobSeekerException extends Exception {
    protected String code;
}
