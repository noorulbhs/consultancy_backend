package com.altrevo.consultancy.dto;

import com.altrevo.consultancy.entity.Enquiry;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryListResponse {
    private List<Enquiry> enquiries;
    private PaginationResponse<Enquiry> pagination;
    private EnquiryStats stats;
}
