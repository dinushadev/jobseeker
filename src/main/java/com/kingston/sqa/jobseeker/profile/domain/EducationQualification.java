package com.kingston.sqa.jobseeker.profile.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EducationQualification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String school;
    private String Degree;

    private String fieldOfStudy;

    private LocalDate startDate;
    private LocalDate endData;

    private String grade;

    private Integer GCSEPass;
    public String description;

    private List<String> skills;


    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EducationQualification that = (EducationQualification) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
