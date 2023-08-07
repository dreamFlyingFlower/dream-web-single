package com.dream.web.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.electric.framework.entity.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 参数管理
 *
 * @author  
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_params")
public class SysParamsEntity extends BaseEntity {

    /**
     * 参数名称
     */
    private String paramName;

    /**
     * 系统参数
     */
    private Integer paramType;

    /**
     * 参数键
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

    /**
     * 备注
     */
    private String remark;


}