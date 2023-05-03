package com.kingston.sqa.jobseeker.profile.domain;

import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.profile.dto.QualificationDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private User user;

    private String headline;
    private String about;

    private String industry;

    private String eductionLevel;

    private List<String> skills;

//    @OneToMany(mappedBy = "profile")
//    @ToString.Exclude
//    private List<EducationQualification> academicQualifications;
//
//    @OneToMany
//    @ToString.Exclude
//    private List<ProfessionalQualification> professionalQualifications;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @Where(clause = "type = 'ACADEMIC'")
    private List<Qualification> academicQualifications;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    @Where(clause = "type = 'PROFESSIONAL'")
    private List<Qualification> professionalQualifications;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    public Profile(Long profileId) {
        this.id = profileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Profile profile = (Profile) o;
        return getId() != null && Objects.equals(getId(), profile.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}
