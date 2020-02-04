package com.javaweb.po;

import java.io.Serializable;

import com.javaweb.annotation.sql.Column;
import com.javaweb.annotation.sql.Table;
import com.javaweb.base.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name="quartz")
public class Quartz extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -4644259284898899146L;
    
    @Column(name="job_id",pk=true)
    private String jobId;//任务id
    
    @Column(name="group_name")
    private String groupName = "quartz";//组

    @Column(name="class_name")
    private String className;//类名

    @Column(name="method_name")
    private String methodName = "execute";//方法名

    @Column(name="param")
    private String param;//参数

    @Column(name="cron_expression")
    private String cronExpression;//cron表达式
    
    @Column(name="execute_start_time")
    private String executeStartTime;//执行开始时间

    @Column(name="status")
    private Integer status;//任务状态(1：正常；2：暂停)

    @Column(name="remark")
    private String remark;//备注

}
