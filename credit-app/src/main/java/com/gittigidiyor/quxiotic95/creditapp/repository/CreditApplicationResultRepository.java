package com.gittigidiyor.quxiotic95.creditapp.repository;

import com.gittigidiyor.quxiotic95.creditapp.entity.CreditApplicationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditApplicationResultRepository extends JpaRepository<CreditApplicationResult, Long> {

}
