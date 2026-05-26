package com.stockwallet.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PortfolioSummaryResponse {

    private String symbol;

    private String assetName;

    private Double quantity;

    private Double averageBuyPrice;

    private Double currentPrice;

    private Double investedAmount;

    private Double currentValue;

    private Double profitLoss;

    private Double profitLossPercent;
}