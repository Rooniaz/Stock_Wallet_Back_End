package com.stockwallet.service;

import com.stockwallet.dto.request.CreateTransactionRequest;
import com.stockwallet.entity.*;
import com.stockwallet.entity.enums.TransactionType;
import com.stockwallet.repository.AssetRepository;
import com.stockwallet.repository.PortfolioRepository;
import com.stockwallet.repository.TransactionRepository;
import com.stockwallet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final UserRepository userRepository;
    private final AssetRepository assetRepository;
    private final PortfolioRepository portfolioRepository;
    private final TransactionRepository transactionRepository;

    public void buy(CreateTransactionRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow();

        Asset asset = assetRepository.findBySymbol(request.getSymbol())
                .orElseThrow();

        Double totalAmount = request.getQuantity() * request.getPrice();

        Transaction transaction = new Transaction();

        transaction.setUser(user);
        transaction.setAsset(asset);
        transaction.setTransactionType(TransactionType.BUY);
        transaction.setQuantity(request.getQuantity());
        transaction.setPrice(request.getPrice());
        transaction.setTotalAmount(totalAmount);
        transaction.setFee(request.getFee());
        transaction.setSourcePlatform(request.getSourcePlatform());

        transactionRepository.save(transaction);

        Portfolio portfolio = portfolioRepository
                .findByUserAndAsset(user, asset)
                .orElse(new Portfolio());

        if (portfolio.getId() == null) {
            portfolio.setUser(user);
            portfolio.setAsset(asset);
            portfolio.setTotalQuantity(0.0);
            portfolio.setAverageBuyPrice(0.0);
            portfolio.setInvestedAmount(0.0);
        }

        Double newQuantity =
                portfolio.getTotalQuantity() + request.getQuantity();

        Double newInvested =
                portfolio.getInvestedAmount() + totalAmount;

        Double avgPrice = newInvested / newQuantity;

        portfolio.setTotalQuantity(newQuantity);
        portfolio.setInvestedAmount(newInvested);
        portfolio.setAverageBuyPrice(avgPrice);

        portfolioRepository.save(portfolio);
    }
}