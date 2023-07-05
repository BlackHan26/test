package com.example.test.dto;

import com.example.test.entity.SalesItemEntity;
import lombok.Data;

@Data
public class SalesItemDto {
    private Long Id;
    private String writer;
    private Integer minPriceWanted;
    private String content;
    private String title;
    private String password;
    private String imgUrl;

    public static SalesItemDto fromEntity(SalesItemEntity entity){
        SalesItemDto dto = new SalesItemDto();
        dto.setId(entity.getId());
        dto.setWriter(entity.getWriter());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setMinPriceWanted((entity.getMinPrice()));
        return dto;
    }
}