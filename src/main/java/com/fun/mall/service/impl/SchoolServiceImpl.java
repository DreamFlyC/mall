package com.fun.mall.service.impl;

import com.fun.mall.entity.School;
import com.fun.mall.persistence.SchoolMapper;
import com.fun.mall.service.ISchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 15:19 2018/12/19
 * @ Description：School服务接口实现
 * @ Modified By：
 * @ Version    : 1.0$
 */
@Service("SchoolServiceImpl")
public class SchoolServiceImpl implements ISchoolService {

    @Autowired
    private SchoolMapper schoolMapper;

    @Override
    public int insert(School school){
        return schoolMapper.insertSelective(school);
    }

    @Override
    public List<School> getList(){
        return schoolMapper.getList();
    }

    @Override
    public int del(){
        return schoolMapper.del();
    }

}
