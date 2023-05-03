package com.kingston.sqa.jobseeker.profile.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private  String institute;

    @Enumerated(EnumType.STRING)
    private QualificationType type; //Academic or Professional

    private String certificate;
    private String certificateType;


    private LocalDate start;
    private LocalDate end;

    private List<String> skills;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;


}
