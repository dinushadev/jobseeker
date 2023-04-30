package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.domain.Qualification;
import com.kingston.sqa.jobseeker.profile.domain.QualificationType;
import com.kingston.sqa.jobseeker.profile.respositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements IProfileService{

    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Profile updateProfile(Profile profile) {

        //set  qualification type
        if (profile.getAcademicQualifications()!= null && profile.getAcademicQualifications().size()>0 ){
            for (Qualification qualification : profile.getAcademicQualifications()) {
                qualification.setType(QualificationType.ACADEMIC);
            }
        }
        if (profile.getProfessionalQualifications()!= null && profile.getProfessionalQualifications().size()>0 ){
            for (Qualification qualification : profile.getProfessionalQualifications()) {
                qualification.setType(QualificationType.PROFESSIONAL);
            }
        }

        return profileRepository.save(profile);
    }
}
