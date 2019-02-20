package com.retail.discountapp.domain;

import com.retail.discountapp.domain.enums.ItemType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String itemName;
    private BigDecimal itemPrice;
    private ItemType itemType;
    @ManyToOne
    @JoinColumn
    private PurchasedItem purchasedItem;

    public Item(String itemName, BigDecimal itemPrice, ItemType itemType) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }
}
