package com.kingston.sqa.jobseeker.profile.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.util.StringUtils;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Skill {
    @Id
    private String id;

    private String label;

    @ManyToMany(mappedBy = "skills")
    @ToString.Exclude
    private Set<Profile> profiles;

    public Skill(String skillLabel) {
        label = StringUtils.capitalize(skillLabel);
        id = skillLabel.toUpperCase().replace(' ', '_');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Skill skill = (Skill) o;
        return getId() != null && getId().equals(skill.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
