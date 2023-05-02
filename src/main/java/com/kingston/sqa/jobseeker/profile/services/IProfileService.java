package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.profile.domain.Profile;

public interface IProfileService {
    Profile updateProfile(Profile profile);

    Profile readProfile(Long userId) throws ProfileNotFoundException;
}
