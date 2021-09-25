package com.gittigidiyor.quxiotic95.creditapp.service.implementation;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditApplicationResultDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.CreditApplicationResult;
import com.gittigidiyor.quxiotic95.creditapp.entity.CreditScore;
import com.gittigidiyor.quxiotic95.creditapp.entity.Customer;
import com.gittigidiyor.quxiotic95.creditapp.mapper.CreditApplicationResultMapper;
import com.gittigidiyor.quxiotic95.creditapp.repository.CreditApplicationResultRepository;
import com.gittigidiyor.quxiotic95.creditapp.repository.CreditScoreRepository;
import com.gittigidiyor.quxiotic95.creditapp.repository.CustomerRepository;
import com.gittigidiyor.quxiotic95.creditapp.service.CreditApplicationResultService;
import com.gittigidiyor.quxiotic95.creditapp.utility.LoanLimitCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditApplicationResultServiceImpl implements CreditApplicationResultService {

    private final CreditApplicationResultRepository creditApplicationResultRepository;
    private final CustomerRepository customerRepository;
    private final CreditScoreRepository creditScoreRepository;

    @Transactional
    @Override
    public CreditApplicationResultDTO applyForLoan(String tckn) {

        Customer foundCustomer = customerRepository.findCustomerByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + tckn + " couldn't found!"));

        CreditScore foundCreditScore = creditScoreRepository.findCreditScoreByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Credit score for Customer with tckn: " + tckn + " not found!"));

        if (!foundCustomer.isUpdated()) {
            throw new RuntimeException("Update the Customer info before applying for Loan!");
        }

        CreditApplicationResult creditApplicationResult = new CreditApplicationResult();

        creditApplicationResult.setCustomerTckn(tckn);
        creditApplicationResult.setCustomerFirstName(foundCustomer.getFirstName());
        creditApplicationResult.setCustomerLastName(foundCustomer.getLastName());
        creditApplicationResult.setCustomerPhoneNumber(foundCustomer.getPhoneNumber());
        creditApplicationResult.setCustomerMonthlyIncome(foundCustomer.getMonthlyIncome());
        double creditLimit = LoanLimitCalculator.calculateLoanLimit(foundCustomer.getMonthlyIncome(), foundCreditScore.getCreditScore());
        creditApplicationResult.setCreditLimit(creditLimit);
        boolean isAccepted = creditLimit != 0;
        creditApplicationResult.setApproved(isAccepted);

        foundCustomer.setUpdated(false);
        customerRepository.save(foundCustomer);

        return CreditApplicationResultMapper.INSTANCE.toCreditApplicationResultDto(creditApplicationResultRepository.save(creditApplicationResult));
    }
}
