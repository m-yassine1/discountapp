package com.retail.discountapp.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
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
}
