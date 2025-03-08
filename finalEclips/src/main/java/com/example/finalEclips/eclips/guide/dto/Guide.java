package com.example.finalEclips.eclips.guide.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Guide {
    private String guideId;
    private String icon;
    private String title;
    private String subtitle;
    private String description;
    private String imageUrl;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
