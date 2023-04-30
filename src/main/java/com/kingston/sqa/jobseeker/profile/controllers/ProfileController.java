package com.kingston.sqa.jobseeker.profile.controllers;

import com.kingston.sqa.jobseeker.profile.domain.Profile;
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
    public ResponseEntity<?> profile(@RequestBody Profile profile, @PathVariable String userId) throws Exception {

        profile.setId(Long.parseLong(userId));
       Profile updatedProfile =  this.profileService.updateProfile( profile);

        return ResponseEntity.ok(updatedProfile);
    }

}

