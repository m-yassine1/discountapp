package com.retail.discountapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class PurchasedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Customer customer;
    @NotNull(message = "Total bill is required")
    private BigDecimal totalBill;
    private LocalDateTime purchaseDate = LocalDateTime.now();
    @OneToMany(mappedBy = "purchasedItem")
    private Set<Item> purchasedItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Set<Item> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(Set<Item> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
}
