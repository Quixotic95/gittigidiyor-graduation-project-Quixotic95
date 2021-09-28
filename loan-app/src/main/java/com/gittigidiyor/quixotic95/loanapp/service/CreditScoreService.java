package com.gittigidiyor.quixotic95.loanapp.service;

import com.gittigidiyor.quixotic95.loanapp.dto.CreditScoreDTO;
import com.gittigidiyor.quixotic95.loanapp.service.generic.GenericService;

public interface CreditScoreService extends GenericService<CreditScoreDTO> {

    CreditScoreDTO generateCreditScore(String tckn);

}
