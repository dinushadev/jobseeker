package com.kingston.sqa.jobseeker.profile.utill;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EducationUtilTest {


    @Test
    void testGetListOfEligibleEducationLevel() {

       List<String> eduList = EducationUtil.getListOfEligibleEducationLevel("Diploma");

       assertEquals(EducationUtil.orderedEducationLevels.subList(1, EducationUtil.orderedEducationLevels.size()), eduList);
    }

    @Test
    void testNotMatchingEducationGetListOfEligibleEducationLevel() {

        List<String> eduList = EducationUtil.getListOfEligibleEducationLevel("notMatching");

        assertEquals(new ArrayList<>(), eduList);
    }
}