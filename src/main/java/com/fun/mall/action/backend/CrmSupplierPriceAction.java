package com.fun.mall.action.backend;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fun.mall.common.RedisClusterPool;
import com.fun.mall.common.ServerResponse;
import com.fun.mall.entity.CrmSupplierPrice;
import com.fun.mall.service.ICrmSupplierPriceService;
import com.fun.mall.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 12:21 2018/12/28
 * @ Description：供应商报价单Controller
 * @ Modified By：
 * @ Version    : 1.0$
 */
@Controller
public class CrmSupplierPriceAction {

    @Autowired
    private ICrmSupplierPriceService crmSupplierPriceService;

    @RequestMapping("insert2Redis.do")
    @ResponseBody
    public ServerResponse save() {
        JedisCluster cluster= RedisClusterPool.getJedisCluster();
        List<CrmSupplierPrice> list=crmSupplierPriceService.getList();
        cluster.hset("priceMap","czp", JSON.toJSON(list).toString());
        return ServerResponse.createBySuccess();
    }


    @RequestMapping(value = "/getsupplierpricelist.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getList() {
        JedisCluster cluster= RedisClusterPool.getJedisCluster();
        String priceMap = cluster.hget("priceMap", "czp");
        List<CrmSupplierPrice> priceList = JsonUtil.stringToObj(priceMap, new TypeReference<List<CrmSupplierPrice>>() {
        });
        if (!priceMap.isEmpty()) {
            return ServerResponse.createBySuccess(priceList);
        } else {
            List<CrmSupplierPrice> obj = crmSupplierPriceService.getList();
            if (obj.size() > 0) {
                return ServerResponse.createBySuccess(obj);
            }
            return ServerResponse.createByErrorMessage("没有数据");
        }
    }

    @RequestMapping(value = "test.do")
    @ResponseBody
    public ServerResponse test(){
        return ServerResponse.createBySuccess("SUCCESS!");
    }
}
