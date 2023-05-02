package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class ProfileDto {

    private Long id;

    private User user;

    private String headline;
    private String about;

    private String industry;

    private String eductionLevel;

    private List<String> skills;


    private List<QualificationDto> academicQualifications;

    private List<QualificationDto> professionalQualifications;

    public Profile toDomain() {

        academicQualifications.stream().map( QualificationDto::toDomain).collect(Collectors.toList());
        
        return  Profile.builder()
                .id(this.id)
                .user(user)
                .headline(this.headline)
                .about(this.about)
                .industry(this.industry)
                .skills(this.skills)
                .build();
    }
}
