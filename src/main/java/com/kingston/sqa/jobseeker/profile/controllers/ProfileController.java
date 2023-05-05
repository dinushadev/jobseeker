package com.kingston.sqa.jobseeker.profile.controllers;

import com.kingston.sqa.jobseeker.api.dto.PageDto;
import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.api.response.BaseResponse;
import com.kingston.sqa.jobseeker.api.response.PageResponse;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.dto.ProfileDto;
import com.kingston.sqa.jobseeker.profile.dto.ProfileSearchDto;
import com.kingston.sqa.jobseeker.profile.services.IProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseeker/profile")
public class ProfileController {
    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    @Autowired
    private IProfileService profileService;

    @PutMapping("/{userId}")
    public ResponseEntity<BaseResponse<ProfileDto>> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable Long userId) throws Exception {

        profileDto.setId(userId);
        Profile profile = profileDto.toDomain();
        profile.setId(userId);

        Profile updatedProfile = this.profileService.updateProfile(profile);

        BaseResponse<ProfileDto> res = new BaseResponse<>();
        res.setData(new ProfileDto(updatedProfile));

        return ResponseEntity.ok(res);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<BaseResponse<ProfileDto>> getProfile(@PathVariable Long userId) throws ProfileNotFoundException {
        try{

            Profile updatedProfile = this.profileService.readProfile(userId);
            ProfileDto profileDto = new ProfileDto(updatedProfile);

            BaseResponse<ProfileDto> res = new BaseResponse<>();
            res.setData(profileDto);

            return ResponseEntity.ok(res);
        }catch (ProfileNotFoundException e){
            logger.error("errorin get profile "+ e.getMessage());
           throw  e;
        }


    }

    @PostMapping("/search")
    public ResponseEntity<?> findProfiles(@RequestBody ProfileSearchDto profileSearch) throws ProfileNotFoundException {


        PageDto<Profile> profilesPage = profileService.searchProfiles(profileSearch);

        BaseResponse<ProfileDto> res = new BaseResponse<>();
        if (profilesPage.getList() != null) {
            List<ProfileDto> profileDtoList = profilesPage.getList().stream().map(ProfileDto::new).toList();
            PageResponse<ProfileDto> pageResponse = new PageResponse<>(profileDtoList, profilesPage.getTotal());

            res.setResult(pageResponse);
        } else {
            throw new ProfileNotFoundException();
        }

        return ResponseEntity.ok(res);

    }


}

