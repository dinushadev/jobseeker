package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class QualificationDto {

    private Long id;

    private  String institute;

    private QualificationType type; //Academic or Professional

    private String certificate;
    private String certificateType;


    private LocalDate start;
    private LocalDate end;

    private List<String> skills;

    public Qualification toDomain(){
        return  Qualification.builder()
                .id(id)
                .institute(this.institute)
                .certificate(this.certificate)
                .certificateType(certificateType)
                .build();
    }

}
