package com.gittigidiyor.quixotic95.loanapp.repository;

import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplicationResult, Long> {

    Optional<List<LoanApplicationResult>> findLoanApplicationResultsByCustomerTckn(String tckn);

    Optional<LoanApplicationResult> findTopLoanApplicationResultByCustomerTcknOrderByCreatedDateDesc(String tckn);

}
