package com.haylion.mp.beans;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

/**
 * JavaBean
 */
@Data
@TableName(value = "tbl_employee")
public class Employee {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String lastName;
    private String email;
    private Integer gender;
    private Integer age;
    private Long time;

    @Version
    private Integer version;
}
