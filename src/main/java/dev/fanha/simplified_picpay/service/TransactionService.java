package dev.fanha.simplified_picpay.service;

import dev.fanha.simplified_picpay.controller.dto.TransactionRequestDTO;
import dev.fanha.simplified_picpay.entity.Transaction;
import dev.fanha.simplified_picpay.entity.User;
import dev.fanha.simplified_picpay.entity.UserType;
import dev.fanha.simplified_picpay.exception.InsufficientBalanceException;
import dev.fanha.simplified_picpay.exception.MerchantsAreNotAllowedToTransferMoneyException;
import dev.fanha.simplified_picpay.exception.UserNotFoundException;
import dev.fanha.simplified_picpay.repository.TransactionRepository;
import dev.fanha.simplified_picpay.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionService(AuthorizationService authorizationService, NotificationService notificationService, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.authorizationService = authorizationService;
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Transaction transferMoney(TransactionRequestDTO transactionDTO) {
        var sender = userRepository.findById(transactionDTO.sender()).orElseThrow(
                () -> new UserNotFoundException(transactionDTO.sender()));

        var receiver = userRepository.findById(transactionDTO.receiver()).orElseThrow(
                () -> new UserNotFoundException(transactionDTO.receiver()));

        validateTransaction(transactionDTO, sender);
        var transactionAmount = transactionDTO.amount();

        sender.setBalance(sender.getBalance().subtract(transactionAmount));
        receiver.setBalance(receiver.getBalance().add(transactionAmount));

        var transaction = new Transaction(transactionAmount, sender, receiver);

        authorizationService.isAuthorized(transaction);
        notificationService.sendNotification(transaction);

        userRepository.save(sender);
        userRepository.save(receiver);
        return transactionRepository.save(transaction);
    }

    public void validateTransaction(TransactionRequestDTO transactionDTO, User sender) {
        if (sender.getUserType().equals(UserType.MERCHANT)) {
            throw new MerchantsAreNotAllowedToTransferMoneyException();
        }

        if (sender.getBalance().compareTo(transactionDTO.amount()) < 0) {
            throw new InsufficientBalanceException();
        }
    }
}
