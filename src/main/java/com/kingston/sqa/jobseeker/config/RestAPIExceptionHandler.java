package com.kingston.sqa.jobseeker.config;

import com.kingston.sqa.jobseeker.api.error.InvalidLoginException;
import com.kingston.sqa.jobseeker.api.error.JobSeekerException;
import com.kingston.sqa.jobseeker.api.error.ProfileNotFoundException;
import com.kingston.sqa.jobseeker.api.response.BaseResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Component
public class RestAPIExceptionHandler
  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
      = {  JobSeekerException.class })
    protected ResponseEntity<Object> handleConflict(HttpServletRequest req,
                                                    JobSeekerException ex) {
        String bodyOfResponse = "This should be application specific";
       BaseResponse<Object> err= new BaseResponse<>();
       err.setSuccess(false);
       err.setErrorCode(ex.getCode());
        if (ex instanceof InvalidLoginException){
            return ResponseEntity.status(401).body(err);
        }else {
            return ResponseEntity.badRequest().body(err);
        }
    }
}