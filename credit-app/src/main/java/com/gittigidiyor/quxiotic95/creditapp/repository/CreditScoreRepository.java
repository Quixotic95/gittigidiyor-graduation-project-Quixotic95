package com.gittigidiyor.quxiotic95.creditapp.repository;

import com.gittigidiyor.quxiotic95.creditapp.entity.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditScoreRepository extends JpaRepository<CreditScore, Long> {

    Optional<CreditScore> findCreditScoreByTckn(String tckn);

}
