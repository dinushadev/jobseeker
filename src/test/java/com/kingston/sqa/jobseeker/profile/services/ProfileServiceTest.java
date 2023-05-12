package com.kingston.sqa.jobseeker.profile.services;

import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.auth.repositories.UserRepository;
import com.kingston.sqa.jobseeker.profile.domain.Profile;
import com.kingston.sqa.jobseeker.auth.domain.*;
import com.kingston.sqa.jobseeker.profile.domain.Skill;
import com.kingston.sqa.jobseeker.profile.respositories.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProfileServiceTest {

    @InjectMocks
    ProfileService profileService;

    @Mock
    SkillService skillService;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    UserRepository userRepository;

    @Test
    void testProfileNotFoundUpdateProfile() {
        Long userId =1L;
        Profile profile = Profile.builder()
                .id(userId)
                .user(User.builder().id(userId).build())
                        .build();
       // when(userRepository.findById(userId)).thenReturn(Optional.empty());
        ProfileNotFoundException thrown = assertThrows(
                ProfileNotFoundException.class, () -> profileService.updateProfile(profile),
                "Profile not found"
        );
    }
    @Test
    void testSuccessfullyUpdateProfile() throws ProfileNotFoundException {

        //UserRepository mockUserRepo = Mockito.mock(UserRepository.class);

        Long userId =1L;
        User exsistingUser = User.builder().
                id(userId)
                .email("tst1@gmail.com")
                .gender("F")
                .firstName("First Name")
                .lastName("LastName1")
                .birthDay(LocalDate.now().minusYears(20))
                .address("My road 123")
                .mobileNumber("494949449")
                .build();

        Profile profile = Profile.builder()
                .id(userId)
                .user(exsistingUser)
                        .build();

        when(userRepository.findById(profile.getId())).thenReturn(Optional.of(exsistingUser));
        when(profileRepository.save(profile)).thenReturn(profile);
        Profile savedProfile = profileService.updateProfile(profile);
        Assertions.assertNotNull(savedProfile);
    }

    @Test
    void testProfileNotFoundReadProfile() {
        Long userId =1L;
        Profile profile = Profile.builder()
                .id(userId)
                .user(User.builder().id(userId).build())
                .build();
        ProfileNotFoundException thrown = assertThrows(
                ProfileNotFoundException.class, () -> profileService.readProfile(profile.getId()),
                "Profile not found"
        );

    }
    @Test
    void testUserAvailableButNotProfileFoundReadProfile() throws ProfileNotFoundException {
        Long userId =1L;
        User existingUserData = User.builder().id(userId).build();
        Profile updateProfile = Profile.builder()
                .id(userId)
                .user(existingUserData)
                .skills(Set.of(new Skill("Java"), new Skill("c#")))
                .build();

        when(profileRepository.findById(userId)).thenReturn(Optional.empty());

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUserData));

        when(profileRepository.save(updateProfile)).thenReturn(updateProfile);

        Profile savedProfile = profileService.updateProfile(updateProfile);
        Assertions.assertNotNull(savedProfile);

    }

    @Test
    void searchProfiles() {
    }
}