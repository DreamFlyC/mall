package com.fun.mall.service;

import com.fun.mall.entity.School;

import java.util.List;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:19 2018/12/19
 * @ Description：School服务接口
 * @ Modified By：
 * @Version: 1.0$
 */
public interface ISchoolService {
    int insert(School school);

    List<School> getList();

    int del();
}
