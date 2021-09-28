package com.gittigidiyor.quixotic95.loanapp.repository;

import com.gittigidiyor.quixotic95.loanapp.entity.Sms;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends MongoRepository<Sms, String> {

}
