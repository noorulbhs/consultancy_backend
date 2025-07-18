package com.altrevo.consultancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnquiryStats {
    private Long totalEnquiries;
    private Long unreadEnquiries;
    private Long thisMonth;
    private Long lastMonth;
}
