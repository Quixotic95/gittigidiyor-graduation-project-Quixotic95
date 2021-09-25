package com.gittigidiyor.quxiotic95.creditapp.service.implementation;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditScoreDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.CreditScore;
import com.gittigidiyor.quxiotic95.creditapp.mapper.CreditScoreMapper;
import com.gittigidiyor.quxiotic95.creditapp.repository.CreditScoreRepository;
import com.gittigidiyor.quxiotic95.creditapp.service.CreditScoreService;
import com.gittigidiyor.quxiotic95.creditapp.utility.CreditScoreGenerator;
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

        Optional<CreditScore> foundCreditScore = creditScoreRepository.findCreditScoreByTckn(tckn);
        if (foundCreditScore.isPresent()) {
            throw new RuntimeException("A credit score for Customer with tckn: " + tckn + " already exists!");
        }

        double creditScore = CreditScoreGenerator.generateCreditScore(tckn);

        CreditScore result = new CreditScore();
        result.setTckn(tckn);
        result.setCreditScore(creditScore);

        return CreditScoreMapper.INSTANCE.toCreditScoreDto(creditScoreRepository.save(result));

    }

    @Override
    public CreditScoreDTO findCreditScoreByTckn(String tckn) {
        return CreditScoreMapper.INSTANCE.toCreditScoreDto(creditScoreRepository.findCreditScoreByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Credit score for Customer with tckn: " + tckn + " not found!")));
    }
}
