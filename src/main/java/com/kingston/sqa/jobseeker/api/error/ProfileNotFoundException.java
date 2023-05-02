package com.kingston.sqa.jobseeker.api.error;

import lombok.Getter;

@Getter
public class ProfileNotFoundException extends Exception{

    private  String code ;
    public ProfileNotFoundException(){
        this.code = "PROFILE_NOT_FOUND";
    }



}
