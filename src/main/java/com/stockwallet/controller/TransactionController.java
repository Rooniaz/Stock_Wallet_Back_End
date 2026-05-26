package com.stockwallet.controller;

import com.stockwallet.dto.request.CreateTransactionRequest;
import com.stockwallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/buy")
    public String buy(
            @RequestBody CreateTransactionRequest request
    ) {

        transactionService.buy(request);

        return "BUY SUCCESS";
    }
}