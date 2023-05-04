package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;

public interface IProfileService {
    Profile updateProfile(Profile profile);

    Profile readProfile(Long userId) throws ProfileNotFoundException;

    PageDto<Profile> searchProfiles(ProfileSearchDto profileSearch);
}
