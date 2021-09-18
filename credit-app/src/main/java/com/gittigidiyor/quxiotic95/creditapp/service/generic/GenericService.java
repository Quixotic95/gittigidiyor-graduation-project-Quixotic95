package com.gittigidiyor.quxiotic95.creditapp.service.generic;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;

public interface GenericService<T> {

    GenericDTO<T> save(T t);

}
