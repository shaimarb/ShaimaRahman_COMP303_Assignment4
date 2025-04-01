package com.sr.assignment;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketOrderRepository extends MongoRepository<MarketOrder, String> {
}