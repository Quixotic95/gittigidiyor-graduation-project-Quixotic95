package com.gittigidiyor.quixotic95.loanapp.repository;


import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {

    Optional<CreditScore> findCreditScoreByCustomerTckn(String customerTckn);

}
