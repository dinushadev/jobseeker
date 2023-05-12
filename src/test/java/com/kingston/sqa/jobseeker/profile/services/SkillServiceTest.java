package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.profile.domain.Skill;
import com.kingston.sqa.jobseeker.profile.respositories.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class SkillServiceTest {


    @InjectMocks
    SkillService skillService;
    @Mock
    SkillRepository skillRepository;
    @Test
    void testSyncSkills() {
        Set<Skill> skills= Set.of(new Skill("Go"), new Skill("react js"));
        when(skillRepository.saveAll(skills)).thenReturn(new ArrayList<>(skills));
    }
}