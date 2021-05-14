package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-24
 */
@Data
@TableName(value = "employee_inf")
@EqualsAndHashCode(callSuper = false)
public class Employee extends Model<Employee> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer deptId;

    private Integer jobId;

    private String name;

    private String cardId;

    private String address;

    private String phone;

    private Integer sexId;

    private Integer educationId;

    /**
     * 创建时间
     */
    private Date createdate;

    private Integer userId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
