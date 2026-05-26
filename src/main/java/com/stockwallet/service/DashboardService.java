package com.stockwallet.service;

import com.stockwallet.dto.response.DashboardSummaryResponse;
import com.stockwallet.entity.Portfolio;
import com.stockwallet.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final PortfolioRepository portfolioRepository;

    public DashboardSummaryResponse getSummary() {

        List<Portfolio> portfolios = portfolioRepository.findAll();

        double totalPortfolioValue = 0;
        double totalInvested = 0;

        for (Portfolio portfolio : portfolios) {

            double currentPrice =
                    portfolio.getAsset().getCurrentPrice() != null
                            ? portfolio.getAsset().getCurrentPrice()
                            : 0;

            double quantity =
                    portfolio.getTotalQuantity() != null
                            ? portfolio.getTotalQuantity()
                            : 0;

            double invested =
                    portfolio.getInvestedAmount() != null
                            ? portfolio.getInvestedAmount()
                            : 0;

            totalPortfolioValue += quantity * currentPrice;

            totalInvested += invested;
        }

        double totalProfitLoss =
                totalPortfolioValue - totalInvested;

        double profitLossPercent = 0;

        if (totalInvested > 0) {

            profitLossPercent =
                    (totalProfitLoss / totalInvested) * 100;
        }

        return DashboardSummaryResponse.builder()
                .totalPortfolioValue(totalPortfolioValue)
                .totalInvested(totalInvested)
                .totalProfitLoss(totalProfitLoss)
                .profitLossPercent(profitLossPercent)
                .build();
    }
}