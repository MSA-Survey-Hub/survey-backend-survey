package com.cloud.survey.config.page;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PageResult<DTO> {
    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    private List<DTO> dtoList;

    public PageResult(Page<DTO> page){
        dtoList = page.stream().collect(Collectors.toList());
        totalPage = page.getTotalPages();
        makePageList(page.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
