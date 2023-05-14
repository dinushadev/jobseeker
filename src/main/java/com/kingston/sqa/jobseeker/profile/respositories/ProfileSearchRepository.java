package com.kingston.sqa.jobseeker.profile.respositories;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.auth.dto.UserType;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.Skill;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;
import com.kingston.sqa.jobseeker.profile.utill.EducationUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import com.kingston.sqa.jobseeker.auth.domain.User;

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

        Join<Profile, User> userJoin = profile.join("user");
        keyFieldPredicates.add(
               cb.equal(userJoin.get("role"), UserType.JOB_SEEKER));

        //Job Sector/Industry
        if (StringUtils.hasText(profileSearch.getIndustry())) {
            keyFieldPredicates.add(
                    cb.equal(cb.lower(profile.get("industry")), profileSearch.getIndustry().toLowerCase()));
        }
        //Education level. should have a list
        if (StringUtils.hasText(profileSearch.getEducationQualification())) {

            Join<Profile, Qualification> eduQulificanSet = profile.join("academicQualifications");
            keyFieldPredicates.add(
                    cb.like(cb.lower(eduQulificanSet.get("certificate")), "%" + profileSearch.getEducationQualification().toLowerCase() + "%"));

        }

        //Professional level. should have a list
        if (StringUtils.hasText(profileSearch.getProfessionalQualification())) {

            Join<Profile, Qualification> professionalQulificanSet = profile.join("professionalQualifications");
            keyFieldPredicates.add(
                    cb.like(cb.lower(professionalQulificanSet.get("certificate")), "%" + profileSearch.getProfessionalQualification().toLowerCase() + "%"));

        }

        //Skill
        if (profileSearch.getSkills() != null && profileSearch.getSkills().size() > 0) {

            Join<Profile, Skill> skillSet = profile.join("skills");
            Set<String> searchSkillList = profileSearch.getSkills().stream().map(s -> s.toUpperCase().replace(' ', '_')).collect(Collectors.toSet());
            keyFieldPredicates.add(skillSet.get("id").in(searchSkillList));


        }

        //GCSE pass
        if (profileSearch.getNoOfGcsePasses() > 0) {
            keyFieldPredicates.add(
                    cb.ge(profile.get("gcsePasses"), profileSearch.getNoOfGcsePasses()));
        }

        //education level
        if (StringUtils.hasText(profileSearch.getEducationLevel())) {
            keyFieldPredicates.add(
                    profile.get("educationLevel").in(EducationUtil.getListOfEligibleEducationLevel(profileSearch.getEducationLevel())));
        }

        //experience
//        if (StringUtils.hasText(profileSearch.getExperience())) {
//
//            Join<Profile, Qualification> exprienceJoin = profile.join("experiences");
//            keyFieldPredicates.add(
//                    cb.like(cb.lower(exprienceJoin.get("position")), "%" + profileSearch.getExperience().toLowerCase() + "%"));
//        }
        if (profileSearch.getExperience() >0){
            //yearsOfExperience
            keyFieldPredicates.add(
                    cb.ge(profile.get("yearsOfExperience"), profileSearch.getExperience()));
        }


        // query.orderBy(cb.desc(order.get("createdAt")));
        query.select(profile).where(cb.and(keyFieldPredicates.toArray(new Predicate[0])));

        List<Profile> list = entityManager.createQuery(query).getResultList();
        page.setList(list);
        page.setTotal(list.size());
        return page;
    }
}
