package com.gittigidiyor.quxiotic95.creditapp.service;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditScoreDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.generic.GenericService;

public interface CreditScoreService extends GenericService<CreditScoreDTO> {

    CreditScoreDTO generateCreditScore(String tckn);

    CreditScoreDTO findCreditScoreByTckn(String tckn);

}
