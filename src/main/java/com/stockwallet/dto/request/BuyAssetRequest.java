package com.stockwallet.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyAssetRequest {

    private Long userId;

    private String symbol;

    private Double quantity;

    private Double price;
}