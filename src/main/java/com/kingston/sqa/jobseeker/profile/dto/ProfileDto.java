package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.profile.domain.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    private Set<String> skills;


    private  Set<ExperienceDto> experiences;
    private Set<QualificationDto> academicQualifications;

    private Set<QualificationDto> professionalQualifications;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.user = profile.getUser();
        this.headline = profile.getHeadline();
        this.industry = profile.getIndustry();
        this.about = profile.getAbout();
        this.eductionLevel = profile.getEductionLevel();


        if (profile.getAcademicQualifications()!=null){
            this.academicQualifications = profile.getAcademicQualifications().stream().map(QualificationDto::new).collect(Collectors.toSet());
        }

        if (profile.getProfessionalQualifications()!=null){
            this.professionalQualifications = profile.getProfessionalQualifications().stream().map(QualificationDto::new).collect(Collectors.toSet());
        }

        if (profile.getExperiences() != null) {
            this.experiences = profile.getExperiences().stream().map(ExperienceDto::new).collect(Collectors.toSet());
        }

        if (profile.getSkills() != null){
            this.skills = profile.getSkills().stream().map(Skill::getLabel).collect(Collectors.toSet());
        }
    }

    public Profile toDomain() {

      Set<Qualification>  academicList = null;
      if (academicQualifications != null){
          academicList = academicQualifications.stream().map( q -> q.toDomain(id, QualificationType.ACADEMIC) ).collect(Collectors.toSet());
      }

        Set<Qualification>  profetionalList = null;
      if (professionalQualifications != null){
          profetionalList = professionalQualifications.stream().map( q -> q.toDomain(id, QualificationType.PROFESSIONAL) ).collect(Collectors.toSet());
      }

      Set<Experience> experienceList = null;
      if (this.experiences != null){
          experienceList = experiences.stream().map(e -> e.toDomain(id)).collect(Collectors.toSet());
      }

      Set<Skill> skillList = null;
      if (this.skills != null) {
          skillList = skills.stream().map(Skill::new).collect(Collectors.toSet());
      }

        return  Profile.builder()
                .id(this.id)
                .user(user)
                .headline(this.headline)
                .about(this.about)
                .industry(this.industry)
                .skills(skillList)
                .academicQualifications(academicList)
                .professionalQualifications(profetionalList)
                .experiences(experienceList)
                .build();
    }


}
