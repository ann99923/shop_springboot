package com.example.shop.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {

    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private int stockNumber;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
}
