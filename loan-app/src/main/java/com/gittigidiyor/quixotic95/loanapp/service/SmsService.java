package com.gittigidiyor.quixotic95.loanapp.service;

import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanapp.entity.Sms;
import com.gittigidiyor.quixotic95.loanapp.service.generic.GenericService;

public interface SmsService extends GenericService<SmsService> {

    Sms sendSms(LoanApplicationResult loanApplicationResult);

}
