package com.retail.discountapp.domain;

import com.retail.discountapp.domain.enums.DiscountTypes;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "Full name is a required field")
    private String fullName;
    private LocalDateTime startDate = LocalDateTime.now();
    private DiscountTypes discountTypes;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<PurchasedItem> purchasedItems;

    public Customer(
            @NotEmpty(message = "Full name is a required field") String fullName,
            DiscountTypes discountTypes) {
        this.fullName = fullName;
        this.discountTypes = discountTypes;
    }
}
