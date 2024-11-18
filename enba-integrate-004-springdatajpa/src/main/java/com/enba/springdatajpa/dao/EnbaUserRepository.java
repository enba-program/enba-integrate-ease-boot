package com.enba.springdatajpa.dao;

import com.enba.springdatajpa.entity.EnbaUser;
import org.springframework.data.repository.CrudRepository;

public interface EnbaUserRepository extends CrudRepository<EnbaUser, Long> {

}
