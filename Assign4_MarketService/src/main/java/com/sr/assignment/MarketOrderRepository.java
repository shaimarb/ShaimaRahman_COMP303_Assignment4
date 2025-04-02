package com.sr.assignment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sr.assignment.model.MarketOrder;

@Repository
public interface MarketOrderRepository extends MongoRepository<MarketOrder, String> {
}