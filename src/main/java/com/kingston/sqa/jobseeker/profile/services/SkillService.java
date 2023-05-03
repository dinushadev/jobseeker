package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.profile.domain.Skill;
import com.kingston.sqa.jobseeker.profile.respositories.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SkillService implements ISkillService{

    @Autowired
    private SkillRepository skillRepository;
    @Override
    public void syncSkills(Set<Skill> skills) {
        skillRepository.saveAll(skills);
    }
}
