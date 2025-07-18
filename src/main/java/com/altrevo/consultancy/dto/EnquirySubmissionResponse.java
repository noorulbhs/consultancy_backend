package com.altrevo.consultancy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnquirySubmissionResponse {
    private Long id;
    private String referenceNumber;
    private LocalDateTime createdAt;
}
