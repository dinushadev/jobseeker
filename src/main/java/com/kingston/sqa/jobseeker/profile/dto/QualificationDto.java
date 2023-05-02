package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.parameters.P;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class QualificationDto {

    private Long id;

    private  String institute;

    private String certificate;
    private String certificateType;

    private LocalDate start;
    private LocalDate end;

    private List<String> skills;

    public Qualification toDomain(Long profileId, QualificationType type){
        Profile profile = new Profile();
        profile.setId(profileId);
        return  Qualification.builder()
                .id(id)
                .institute(this.institute)
                .certificate(this.certificate)
                .certificateType(certificateType)
                .start(start)
                .type(type)
                .end(end)
                .profile(profile)
                .skills(skills)
                .build();
    }

}
