package com.sr.assignment;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sr.assignment.models.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}