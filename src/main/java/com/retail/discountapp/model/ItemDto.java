package com.retail.discountapp.model;

import com.retail.discountapp.domain.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String itemName;
    private BigDecimal itemPrice;
    private ItemType itemType;
}
