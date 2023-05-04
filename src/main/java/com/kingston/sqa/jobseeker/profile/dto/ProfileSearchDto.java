package com.kingston.sqa.jobseeker.profile.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileSearchDto {
    private  String industry;
    private String educationLevel;
    private Integer noOfGcsePasses;
    private List<String> skills;
    private String skillExperience;
    private String educationQualification;
    private String professionalQualification;


}
