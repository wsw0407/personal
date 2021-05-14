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
@TableName(value = "contract_inf")
@EqualsAndHashCode(callSuper = false)
public class Contract extends Model<Contract> {

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

    @TableField("STATUS_ID")
    private Integer statusId;

    @TableField("EMP_ID")
    private Integer empId;

    @TableField(exist = false)
    private String empname;

    @TableField("TRAIN_CONTRACT")
    private Integer trainContract;

    @TableField("LABOR_CONTRACT")
    private Integer laborContract;

    @TableField("CREATE_DATE")
    private Date createDate;

    @TableField("CONFIDENTIALITY_CONTRACT")
    private Integer confidentialityContract;

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
