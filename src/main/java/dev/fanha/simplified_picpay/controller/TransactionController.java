package dev.fanha.simplified_picpay.controller;

import dev.fanha.simplified_picpay.controller.dto.TransactionRequestDTO;
import dev.fanha.simplified_picpay.entity.Transaction;
import dev.fanha.simplified_picpay.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transfer")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> transferMoney(@RequestBody @Valid TransactionRequestDTO transactionDTO) {
        var transaction = transactionService.transferMoney(transactionDTO);
        return ResponseEntity.ok(transaction);
    }
}
