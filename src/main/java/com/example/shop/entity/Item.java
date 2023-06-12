package com.example.shop.entity;

import com.example.shop.dto.ItemFormDto;
import com.example.shop.exception.OutOfStockException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long id;    // 상품코드

    @Column(nullable = false, length = 50)
    private String itemNm;      // 상품명

    @Column(name = "price", nullable = false)
    private int price;    // 가격

    @Column(nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus sellStatCd;      // 상품 판매상태

    @Column
    private LocalDateTime regTime;

    @Column
    private LocalDateTime updateTime;

    @Builder
    public Item(String itemNm, int price, int stockNumber, String itemDetail, ItemSellStatus sellStarCd, LocalDateTime regTime, LocalDateTime updateTime){
        this.itemNm = itemNm;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.sellStatCd = sellStarCd;
        this.regTime = regTime;
        this.updateTime = updateTime;
    }

    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.sellStatCd = itemFormDto.getSellStatCd();
    }

    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock < 0){
          throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }

    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }
}
