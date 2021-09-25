package com.gittigidiyor.quxiotic95.creditapp.mapper;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditApplicationResultDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.CreditApplicationResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditApplicationResultMapper {

    CreditApplicationResultMapper INSTANCE = Mappers.getMapper(CreditApplicationResultMapper.class);

    CreditApplicationResultDTO toCreditApplicationResultDto (CreditApplicationResult creditApplicationResult);

}
