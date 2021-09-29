package com.gittigidiyor.quixotic95.loanapp.service;

import com.gittigidiyor.quixotic95.loanapp.dto.LoanApplicationResultDTO;
import com.gittigidiyor.quixotic95.loanapp.service.generic.GenericService;

import java.util.List;

public interface LoanApplicationService extends GenericService<LoanApplicationResultDTO> {

    LoanApplicationResultDTO applyForLoanByTckn(String tckn);

    List<LoanApplicationResultDTO> findLoanApplicationsByTckn(String tckn);

    LoanApplicationResultDTO findLastLoanApplicationResultOfCustomerByTckn(String tckn);

}
