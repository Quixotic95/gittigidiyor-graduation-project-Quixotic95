package com.gittigidiyor.quxiotic95.creditapp.mapper;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditScoreDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.CreditScore;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditScoreMapper {

    CreditScoreMapper INSTANCE = Mappers.getMapper(CreditScoreMapper.class);

    CreditScore toCreditScoreEntity(CreditScoreDTO creditScoreDTO);

    CreditScoreDTO toCreditScoreDto(CreditScore creditScore);

}
