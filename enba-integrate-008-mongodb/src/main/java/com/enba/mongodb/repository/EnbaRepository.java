package com.enba.mongodb.repository;

import com.enba.mongodb.document.EnbaUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnbaRepository extends MongoRepository<EnbaUser, Long> {

  EnbaUser findByUserName(String username);

}
