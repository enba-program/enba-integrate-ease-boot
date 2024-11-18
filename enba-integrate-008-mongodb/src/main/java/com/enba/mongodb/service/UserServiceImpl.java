package com.enba.mongodb.service;


import com.enba.mongodb.document.EnbaUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

  @Autowired
  private MongoTemplate mongoTemplate;


  public void insertUser(EnbaUser user) {
    mongoTemplate.insert(user);
  }


  public void insertUser(List<EnbaUser> userList) {
    mongoTemplate.insert(userList, EnbaUser.class);
  }


  public void deleteUser(Long userId) {
    mongoTemplate.remove(Query.query(Criteria.where("id").is(userId)), EnbaUser.class);
  }

  public EnbaUser getUser(Long userId) {
    return mongoTemplate.findById(userId, EnbaUser.class);
  }


}
