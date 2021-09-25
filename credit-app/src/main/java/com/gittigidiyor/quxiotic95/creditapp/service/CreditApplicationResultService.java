package com.gittigidiyor.quxiotic95.creditapp.service;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditApplicationResultDTO;
import com.gittigidiyor.quxiotic95.creditapp.dto.CreditScoreDTO;
import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.generic.GenericService;

public interface CreditApplicationResultService extends GenericService<CreditApplicationResultDTO> {

    CreditApplicationResultDTO applyForLoan(String tckn);

}
