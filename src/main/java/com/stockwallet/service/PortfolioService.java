package com.stockwallet.service;

import com.stockwallet.dto.response.PortfolioSummaryResponse;
import com.stockwallet.entity.Portfolio;
import com.stockwallet.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public List<PortfolioSummaryResponse> getUserPortfolio() {

        List<Portfolio> portfolios = portfolioRepository.findAll();

        return portfolios.stream()
                .map(portfolio -> {

                    Double currentPrice =
                            portfolio.getAsset().getCurrentPrice() != null
                                    ? portfolio.getAsset().getCurrentPrice()
                                    : 0.0;

                    Double quantity =
                            portfolio.getTotalQuantity() != null
                                    ? portfolio.getTotalQuantity()
                                    : 0.0;

                    Double investedAmount =
                            portfolio.getInvestedAmount() != null
                                    ? portfolio.getInvestedAmount()
                                    : 0.0;

                    Double currentValue =
                            quantity * currentPrice;

                    Double profitLoss =
                            currentValue - investedAmount;

                    Double profitPercent = 0.0;

                    if (investedAmount > 0) {
                        profitPercent =
                                (profitLoss / investedAmount) * 100;
                    }

                    return PortfolioSummaryResponse.builder()
                            .symbol(portfolio.getAsset().getSymbol())
                            .assetName(portfolio.getAsset().getName())
                            .quantity(quantity)
                            .averageBuyPrice(portfolio.getAverageBuyPrice())
                            .currentPrice(currentPrice)
                            .investedAmount(investedAmount)
                            .currentValue(currentValue)
                            .profitLoss(profitLoss)
                            .profitLossPercent(profitPercent)
                            .build();

                }).toList();
    }
}