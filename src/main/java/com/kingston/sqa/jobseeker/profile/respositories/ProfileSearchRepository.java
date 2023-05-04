package com.kingston.sqa.jobseeker.profile.respositories;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.Skill;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ProfileSearchRepository implements IProfileSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public PageDto<Profile> filterProfile(ProfileSearchDto profileSearch) {

        PageDto<Profile> page = new PageDto<>();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Profile> query = cb.createQuery(Profile.class);
        Root<Profile> profile = query.from(Profile.class);

        List<Predicate> keyFieldPredicates = new ArrayList<>();
        List<Predicate> namePredicates = new ArrayList<>();

        //Job Sector/Industry
        if (StringUtils.hasText(profileSearch.getIndustry())) {
            keyFieldPredicates.add(
                    cb.equal(cb.lower(profile.get("industry")), profileSearch.getIndustry().toLowerCase()));
        }
        //Education level. should have a list
        if (StringUtils.hasText(profileSearch.getEducationQualification())){

            Join<Profile, Qualification> eduQulificanSet = profile.join("academicQualifications");
            keyFieldPredicates.add(
                    cb.like(cb.lower(eduQulificanSet.get("certificate")), profileSearch.getEducationQualification().toLowerCase()));

        }

   //Professional level. should have a list
        if (StringUtils.hasText(profileSearch.getProfessionalQualification())){

            Join<Profile, Qualification> professionalQulificanSet = profile.join("professionalQualifications");
            keyFieldPredicates.add(
                    cb.like(cb.lower(professionalQulificanSet.get("certificate")), profileSearch.getProfessionalQualification().toLowerCase()));

        }

        //Skill
        if (profileSearch.getSkills() != null && profileSearch.getSkills().size()>0){

            Join<Profile, Skill> skillSet = profile.join("skills");
            Set<String> searchSkillList = profileSearch.getSkills().stream().map(s -> s.toUpperCase().replace(' ','_')).collect(Collectors.toSet());
         keyFieldPredicates.add(skillSet.get("id").in(searchSkillList));


        }



       // query.orderBy(cb.desc(order.get("createdAt")));
        query.select(profile).where(cb.and(keyFieldPredicates.toArray(new Predicate[0])));

        List<Profile>  list = entityManager.createQuery(query).getResultList();
        page.setList(list);
        page.setTotal(list.size());
        return page;
    }
}