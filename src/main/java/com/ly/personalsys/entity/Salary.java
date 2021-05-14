package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-28
 */
@Data
@TableName(value = "salary_inf")
@EqualsAndHashCode(callSuper = false)
public class Salary extends Model<Salary> {

    private static final long serialVersionUID=1L;

      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("DEPT_ID")
    private Integer deptId;

    @TableField(exist = false)
    private String deptname;

    @TableField("JOB_ID")
    private Integer jobId;

    @TableField(exist = false)
    private String jobname;

    @TableField("EMP_ID")
    private Integer empId;

    @TableField(exist = false)
    private String empname;

    @TableField("SALARY_STATION")
    private Integer salaryStation;

    @TableField("SALARY_LEVEL")
    private Integer salaryLevel;

    @TableField("SENIORITY_PAY")
    private Integer seniorityPay;

    @TableField("PERFORMANCE")
    private Integer performance;

    @TableField("INDIVIDUAL_INCOME")
    private Float individualIncome;

    @TableField("CREATE_DATE")
    private Date createDate;

    public String getCreatTimeStr(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (this.createDate != null){
            return sdf.format(this.createDate);
        }else {
            return  null;
        }
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
