package com.kingston.sqa.jobseeker.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse<T> {
    private boolean success = true;
    private String errorCode;
    private T data;
    private List<T> result;

}
