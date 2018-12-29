package com.fun.mall.service.impl;

import com.fun.mall.entity.CrmSupplierPrice;
import com.fun.mall.persistence.CrmSupplierPriceMapper;
import com.fun.mall.service.ICrmSupplierPriceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 12:23 2018/12/28
 * @ Description：
 * @ Modified By：
 * @ Version    : $
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CrmSupplierPriceServiceImpl implements ICrmSupplierPriceService {

    @Resource
    private CrmSupplierPriceMapper crmSupplierPriceMapper;


    @Override
    public List<CrmSupplierPrice> getList(Map<String, Object> map) {
        return crmSupplierPriceMapper.getList(map);
    }

    @Override
    public List<CrmSupplierPrice> getList() {
        return crmSupplierPriceMapper.getList();
    }
}
