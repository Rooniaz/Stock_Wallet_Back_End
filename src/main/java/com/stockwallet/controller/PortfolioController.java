package com.stockwallet.controller;

import com.stockwallet.dto.response.PortfolioSummaryResponse;
import com.stockwallet.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @GetMapping
    public List<PortfolioSummaryResponse> getPortfolio() {

        return portfolioService.getUserPortfolio();
    }
}