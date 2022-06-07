package com.study.lab3.dto;

import com.study.lab3.entity.Item;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;

@Data
@Builder
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Integer price;
    private Integer bnPrice;
    private String url;
    private String image;
    private String vendor;

    public static ItemDto fromEntity(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .categoryId(item.getCategory().getId())
                .price(item.getPrice())
                .bnPrice(item.getBnPrice())
                .url(item.getUrl())
                .image(item.getImage())
                .vendor(item.getVendor())
                .build();
    }
}
