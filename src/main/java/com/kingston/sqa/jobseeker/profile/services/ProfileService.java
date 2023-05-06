package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.auth.domain.User;
import com.kingston.sqa.jobseeker.auth.repositories.UserRepository;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;
import com.kingston.sqa.jobseeker.profile.respositories.IProfileSearchRepository;
import com.kingston.sqa.jobseeker.profile.respositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ProfileService implements IProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private IProfileSearchRepository profileSearchRepository;


    @Autowired
    private ISkillService skillService;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Profile updateProfile(Profile profile) throws ProfileNotFoundException {

        //set  qualification type
        if (profile.getAcademicQualifications() != null && profile.getAcademicQualifications().size() > 0) {
            for (Qualification qualification : profile.getAcademicQualifications()) {
                qualification.setType(QualificationType.ACADEMIC);
            }
        }
        if (profile.getProfessionalQualifications() != null && profile.getProfessionalQualifications().size() > 0) {
            for (Qualification qualification : profile.getProfessionalQualifications()) {
                qualification.setType(QualificationType.PROFESSIONAL);
            }
        }
        Optional<User> exsistngUserOpt = userRepository.findById(profile.getId());
        User exsistingUser =  exsistngUserOpt.orElseThrow(ProfileNotFoundException::new);
    //update users
        if (profile.getUser() != null && profile.getUser().getId()!= null){

           if (StringUtils.hasText(profile.getUser().getEmail())){
               exsistingUser.setEmail(profile.getUser().getEmail());
           }
            if (StringUtils.hasText(profile.getUser().getGender())){
                exsistingUser.setGender(profile.getUser().getGender());
            }
            if (StringUtils.hasText(profile.getUser().getFirstName())){
                exsistingUser.setFirstName(profile.getUser().getFirstName());
            }
            if (StringUtils.hasText(profile.getUser().getLastName())){
                exsistingUser.setLastName(profile.getUser().getLastName());
            }
            if (profile.getUser().getBirthDay()!= null){
                exsistingUser.setBirthDay(profile.getUser().getBirthDay());
            }
            if (StringUtils.hasText(profile.getUser().getAddress())){
                exsistingUser.setAddress(profile.getUser().getAddress());
            }
            if (StringUtils.hasText(profile.getUser().getMobileNumber())){
                exsistingUser.setMobileNumber(profile.getUser().getMobileNumber());
            }
            this.userRepository.save(profile.getUser());
        } else {

            profile.setUser(exsistingUser);
        }

        //update the still list in skill table
        this.skillService.syncSkills(profile.getSkills());

        return profileRepository.save(profile);
    }

    @Override
    public Profile readProfile(Long userId) throws ProfileNotFoundException {

        Optional<Profile> profileOptional = profileRepository.findById(userId);

        if (profileOptional.isEmpty()){
           User user =  userRepository.findById(userId).orElseThrow(ProfileNotFoundException::new);
           Profile profile = new Profile();
           profile.setUser(user);
           return profile;
        }
        return  profileOptional.get();
    }

    @Override
    public PageDto<Profile> searchProfiles(ProfileSearchDto profileSearch) {
        return profileSearchRepository.filterProfile(profileSearch);


    }
}
