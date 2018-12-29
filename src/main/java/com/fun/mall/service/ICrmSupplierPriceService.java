package com.fun.mall.service;

import com.fun.mall.entity.CrmSupplierPrice;

import java.util.List;
import java.util.Map;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 12:23 2018/12/28
 * @ Description：供应商报价单Service接口
 * @ Modified By：
 * @ Version    : 1.0$
 */
public interface ICrmSupplierPriceService {

    List<CrmSupplierPrice> getList(Map<String, Object> map);

    List<CrmSupplierPrice> getList();
}
