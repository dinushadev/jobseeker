package com.kingston.sqa.jobseeker.profile.dto;

import com.kingston.sqa.jobseeker.profile.domain.Skill;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SkillDto {
    private String id;
    private String label;

    public SkillDto(Skill s) {
        this.id = s.getId();
        this.label = s.getLabel();
    }

    public Skill toDomain(Long profileId) {
        //  List<Profile> profileList = ti
        return Skill.builder()
                .id(this.id)
                .label(this.label)
                //    .profile(new Profile(profileId))
                .build();
    }
}
