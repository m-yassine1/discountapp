package com.retail.discountapp.domain;

import com.retail.discountapp.domain.enums.DiscountTypes;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Full name is a required field")
    private String fullName;
    private LocalDateTime startDate;
    private DiscountTypes discountTypes;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PurchasedItem> purchasedItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public DiscountTypes getDiscountTypes() {
        return discountTypes;
    }

    public void setDiscountTypes(DiscountTypes discountTypes) {
        this.discountTypes = discountTypes;
    }

    public Set<PurchasedItem> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(Set<PurchasedItem> purchasedItems) {
        this.purchasedItems = purchasedItems;
    }
}
