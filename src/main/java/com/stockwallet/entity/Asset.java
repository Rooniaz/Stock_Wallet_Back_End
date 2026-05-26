package com.stockwallet.entity;

import com.stockwallet.entity.enums.AssetType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "assets")
@Getter
@Setter
public class Asset extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String symbol;

    private String name;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    private Double currentPrice;

    private Double dayChangePercent;

    private String imageUrl;
}