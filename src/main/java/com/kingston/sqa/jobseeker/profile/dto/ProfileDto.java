package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.profile.domain.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
public class ProfileDto {

    private Long id;

    private User user;

    private String headline;
    private String about;

    private String industry;

    private String eductionLevel;

    private List<String> skills;


    private  List<ExperienceDto> experiences;
    private List<QualificationDto> academicQualifications;

    private List<QualificationDto> professionalQualifications;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.user = profile.getUser();
        this.headline = profile.getHeadline();
        this.industry = profile.getIndustry();
        this.about = profile.getAbout();
        this.eductionLevel = profile.getEductionLevel();


        if (profile.getAcademicQualifications()!=null){
            this.academicQualifications = profile.getAcademicQualifications().stream().map(QualificationDto::new).toList();
        }

        if (profile.getProfessionalQualifications()!=null){
            this.professionalQualifications = profile.getProfessionalQualifications().stream().map(QualificationDto::new).toList();
        }

        if (profile.getExperiences() != null) {
            this.experiences = profile.getExperiences().stream().map(ExperienceDto::new).toList();
        }
    }

    public Profile toDomain() {

      List<Qualification>  academicList = null;
      if (academicQualifications != null){
          academicList = academicQualifications.stream().map( q -> q.toDomain(id, QualificationType.ACADEMIC) ).toList();
      }

        List<Qualification>  profetionalList = null;
      if (professionalQualifications != null){
          profetionalList = professionalQualifications.stream().map( q -> q.toDomain(id, QualificationType.PROFESSIONAL) ).toList();
      }

      List<Experience> experienceList = null;
      if (this.experiences != null){
          experienceList = experiences.stream().map(e -> e.toDomain(id)).toList();
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
                .experiences(experienceList)
                .build();
    }
}
