package com.kingston.sqa.jobseeker.profile.utill;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


public class EducationUtil {

  public static final List<String> orderedEducationLevels =List.of (
            "Certificate",
            "Diploma",
            "Associate Degree",
            "Bachelor's Degree",
            "Master's Degree",
            "Doctoral Degree");

    public static List<String> getListOfEligibleEducationLevel(String eduLevel){

            for (int i =0; i< orderedEducationLevels.size(); i ++){
                if (eduLevel.equals(orderedEducationLevels.get(i))){
                    return orderedEducationLevels.subList(i, orderedEducationLevels.size());
                }
            }

        return List.of();
    }
}
