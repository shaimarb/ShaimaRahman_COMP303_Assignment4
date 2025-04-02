package com.sr.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.assignment.TransactionRepository;
import com.sr.assignment.DTO.OrderDTO;
import com.sr.assignment.models.Transaction;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction processTransaction(OrderDTO orderDTO) {
        System.out.println("Received new order: " + orderDTO);

     // Assigning default status 
        String defaultStatus = "RECEIVED";  
        
        Transaction transaction = new Transaction(
            orderDTO.getUserId(),
            orderDTO.getStockSymbol(),
            orderDTO.getQuantity(),
            orderDTO.getPricePerUnit(),
            orderDTO.getOrderType(),
            defaultStatus
       );

        Transaction savedTransaction = transactionRepository.save(transaction);
        System.out.println("Transaction saved successfully: " + savedTransaction);

        return savedTransaction;
    }
}
