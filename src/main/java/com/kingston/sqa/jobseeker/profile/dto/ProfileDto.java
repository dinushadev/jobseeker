package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.profile.domain.EducationQualification;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
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

      List<Qualification>  academicList = null;
      if (academicQualifications != null){
          academicList = academicQualifications.stream().map( q -> q.toDomain(id, QualificationType.ACADEMIC) ).toList();
      }

        List<Qualification>  profetionalList = null;
      if (professionalQualifications != null){
          profetionalList = professionalQualifications.stream().map( q -> q.toDomain(id, QualificationType.PROFESSIONAL) ).toList();
      }

        return  Profile.builder()
                .id(this.id)
                .user(user)
                .headline(this.headline)
                .about(this.about)
                .industry(this.industry)
                .skills(this.skills)
                .academicQualifications(academicList)
                .professionalQualifications(profetionalList)
                .build();
    }
}
