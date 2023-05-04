package com.kingston.sqa.jobseeker.profile.respositories;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;


public interface IProfileSearchRepository {
    PageDto<Profile> filterProfile(ProfileSearchDto profileSearch);
}
