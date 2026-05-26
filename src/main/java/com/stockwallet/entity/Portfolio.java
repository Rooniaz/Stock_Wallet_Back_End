package com.stockwallet.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portfolios")
@Getter
@Setter
public class Portfolio extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private Asset asset;

    private Double totalQuantity;

    private Double averageBuyPrice;

    private Double investedAmount;

    private Double currentValue;

    private Double profitLoss;

    private Double profitLossPercent;
}