package com.stockwallet.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DashboardSummaryResponse {

    private Double totalPortfolioValue;

    private Double totalInvested;

    private Double totalProfitLoss;

    private Double profitLossPercent;
}