package com.kingston.sqa.jobseeker.profile.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QualificationDto {

    private Long id;

    private String institute;

    private String certificate;
    private String certificateType;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private LocalDate start;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate end;

    private List<String> skills;

    public QualificationDto(Qualification q) {
        this.id = q.getId();
        this.institute = q.getInstitute();
        this.certificate = q.getCertificate();
        this.certificateType = q.getCertificateType();
        this.start = q.getStart();
        this.end = q.getEnd();
        this.skills = q.getSkills();
    }

    public Qualification toDomain(Long profileId, QualificationType type) {
        Profile profile = new Profile();
        profile.setId(profileId);
        return Qualification.builder()
                .id(id)
                .institute(this.institute)
                .certificate(this.certificate)
                .certificateType(this.certificateType)
                .start(start)
                .type(type)
                .end(end)
                .profile(profile)
                .skills(skills)
                .build();
    }

}
