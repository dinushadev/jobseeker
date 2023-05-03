package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.profile.domain.Skill;

import java.util.Set;

public interface ISkillService {

    void syncSkills(Set<Skill> skills);
}
