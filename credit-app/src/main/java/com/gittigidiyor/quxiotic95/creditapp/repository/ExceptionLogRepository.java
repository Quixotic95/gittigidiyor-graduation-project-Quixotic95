package com.gittigidiyor.quxiotic95.creditapp.repository;

import com.gittigidiyor.quxiotic95.creditapp.entity.log.ExceptionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLogRepository extends JpaRepository<ExceptionLog, Long> {
}
