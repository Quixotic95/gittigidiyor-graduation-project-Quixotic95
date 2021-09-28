package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanapp.entity.Sms;
import com.gittigidiyor.quixotic95.loanapp.repository.SmsRepository;
import com.gittigidiyor.quixotic95.loanapp.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class SmsServiceImpl implements SmsService {

    private final SmsRepository smsRepository;

    @Override
    public Sms sendSms(LoanApplicationResult loanApplicationResult) {

        log.info("inside SmsServiceImpl sendSms");

        log.info(loanApplicationResult.toString());

        Sms sms = new Sms(loanApplicationResult.getCustomerFirstName(),
                loanApplicationResult.getCustomerLastName(),
                loanApplicationResult.getCreatedDate(),
                loanApplicationResult.isApproved(),
                loanApplicationResult.getCreditLimit(),
                loanApplicationResult.getCustomerPhoneNumber());

        return smsRepository.save(sms);

    }

}
