package com.kingston.sqa.jobseeker.api.error;

import lombok.Getter;

@Getter
public class ProfileNotFoundException extends JobSeekerException {



    public ProfileNotFoundException() {
        this.code = "PROFILE_NOT_FOUND";
    }


}
