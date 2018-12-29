package com.fun.mall.persistence;

import com.fun.mall.entity.CrmSupplierPrice;

import java.util.List;
import java.util.Map;

public interface CrmSupplierPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CrmSupplierPrice record);

    int insertSelective(CrmSupplierPrice record);

    CrmSupplierPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CrmSupplierPrice record);

    int updateByPrimaryKey(CrmSupplierPrice record);

    List<CrmSupplierPrice> getList(Map<String, Object> map);

    List<CrmSupplierPrice> getList();
}