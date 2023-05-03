package com.kingston.sqa.jobseeker.profile.respositories;

import com.kingston.sqa.jobseeker.profile.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, String> {

}
