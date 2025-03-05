package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApplyStatusDto {

    private String storeName;
    private Boolean priceApproval;
    private Boolean cleanlinessApproval;
    private String finalApprovalStatus;
    private LocalDate updatedAt;

    public void setPriceApproval(String priceApproval) {
        this.priceApproval = "T".equals(priceApproval);
    }

    public void setCleanlinessApproval(String cleanlinessApproval) {
        this.cleanlinessApproval = "T".equals(cleanlinessApproval);
    }

}
