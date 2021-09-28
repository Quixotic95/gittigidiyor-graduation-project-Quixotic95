package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.dto.CreditScoreDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.CreditScore;
import com.gittigidiyor.quixotic95.loanapp.mapper.CreditScoreMapper;
import com.gittigidiyor.quixotic95.loanapp.repository.CreditScoreRepository;
import com.gittigidiyor.quixotic95.loanapp.service.CreditScoreService;
import com.gittigidiyor.quixotic95.loanapp.utility.CreditScoreGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreditScoreServiceImpl implements CreditScoreService {

    private final CreditScoreRepository creditScoreRepository;

    @Transactional
    @Override
    public CreditScoreDTO generateCreditScore(String tckn) {

        Optional<CreditScore> foundCreditScore = creditScoreRepository.findCreditScoreByCustomerTckn(tckn);
        if (foundCreditScore.isPresent()) {
            return CreditScoreMapper.INSTANCE.toCreditScoreDto(foundCreditScore.get());
        }

        double creditScore = CreditScoreGenerator.generateCreditScore(tckn);

        CreditScore result = new CreditScore();
        result.setCustomerTckn(tckn);
        result.setCreditScore(creditScore);

        return CreditScoreMapper.INSTANCE.toCreditScoreDto(creditScoreRepository.save(result));

    }

}
