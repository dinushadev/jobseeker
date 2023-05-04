package com.kingston.sqa.jobseeker.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<T> {

    private Integer total;
    private Integer currentPage;
    private Integer totalPages;
    private List<T> list;

}
