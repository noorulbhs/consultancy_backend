package com.altrevo.consultancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
    
    private List<T> content;
    private Long totalElements;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Boolean first;
    private Boolean last;
    
    public static <T> PaginationResponse<T> of(List<T> content, Long totalElements, Integer page, Integer size) {
        Integer totalPages = (int) Math.ceil((double) totalElements / size);
        return PaginationResponse.<T>builder()
                .content(content)
                .totalElements(totalElements)
                .page(page)
                .size(size)
                .totalPages(totalPages)
                .first(page == 0)
                .last(page >= totalPages - 1)
                .build();
    }
}
