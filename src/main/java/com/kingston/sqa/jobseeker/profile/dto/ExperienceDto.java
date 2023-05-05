package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.profile.domain.Experience;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class ExperienceDto {

    private Long id;

    private String position;
    //private String employmentType;

    private String organization;
    private String location;

    private LocalDate start;
    private LocalDate end;

    private String description;

    private List<String> skills;

    public ExperienceDto(Experience e) {
        this.id = e.getId();
        this.position = e.getPosition();
        this.organization = e.getOrganization();
        this.location = e.getLocation();
        this.start = e.getStart();
        this.end = e.getEnd();
        this.description = e.getDescription();
        this.skills = e.getSkills();
    }

    public Experience toDomain(Long profileId) {
        return Experience.builder()
                .id(this.id)
                .description(this.description)
                .organization(this.organization)
                .position(this.position)
                .location(this.location)
                .start(this.start)
                .end(this.end)
                .skills(this.skills)
                .profile(new Profile(profileId))
                .build();
    }


}
