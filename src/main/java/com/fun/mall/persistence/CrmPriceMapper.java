package com.fun.mall.persistence;

import com.fun.mall.entity.CrmPrice;
import com.fun.mall.entity.CrmPriceExample;

public interface CrmPriceMapper {
    long countByExample(CrmPriceExample example);

    int delById(Integer id);

    int save(CrmPrice record);

    int saveSelective(CrmPrice record);

    CrmPrice getById(Integer id);

    int editById(CrmPrice record);

    int edit(CrmPrice record);
}