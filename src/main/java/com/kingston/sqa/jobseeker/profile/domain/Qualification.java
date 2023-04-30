package com.kingston.sqa.jobseeker.profile.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String institute;

    private QualificationType type; //Academic or Professional

    private String certificate;
    private String certificateType;

    private LocalDate start;
    private LocalDate end;

    private List<String> skills;
}
