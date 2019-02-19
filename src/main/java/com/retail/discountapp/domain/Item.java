package com.retail.discountapp.domain;

import com.retail.discountapp.domain.enums.ItemType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public PurchasedItem getPurchasedItem() {
        return purchasedItem;
    }

    public void setPurchasedItem(PurchasedItem purchasedItem) {
        this.purchasedItem = purchasedItem;
    }
}
