package com.gittigidiyor.quixotic95.loanapp.repository;

import com.gittigidiyor.quixotic95.loanapp.entity.log.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
}
