package com.fun.mall.persistence;

import com.fun.mall.entity.PayInfo;

public interface PayInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    int insert(PayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    int insertSelective(PayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    PayInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(PayInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mmall_pay_info
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(PayInfo record);
}