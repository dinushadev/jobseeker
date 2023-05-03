package com.kingston.sqa.jobseeker.profile.controllers;

import com.kingston.sqa.jobseeker.api.response.BaseResponse;
import com.kingston.sqa.jobseeker.auth.dto.JWTDto;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.profile.dto.ProfileDto;
import com.kingston.sqa.jobseeker.profile.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/jobseeker/profile")
public class ProfileController {

    @Autowired
    private IProfileService profileService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileDto profileDto, @PathVariable Long userId) throws Exception {

        profileDto.setId(userId);
        Profile profile = profileDto.toDomain();
        profile.setId(userId);

       Profile updatedProfile =  this.profileService.updateProfile( profile);

        BaseResponse<ProfileDto> res = new BaseResponse<>();
        res.setData(new ProfileDto(updatedProfile));

        return ResponseEntity.ok(res);
    }
  @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile( @PathVariable Long userId) throws Exception {


       Profile updatedProfile =  this.profileService.readProfile(userId);

       ProfileDto profileDto =new ProfileDto(updatedProfile);

        BaseResponse<ProfileDto> res = new BaseResponse<>();
        res.setData(profileDto);

        return ResponseEntity.ok(res);
    }

}

