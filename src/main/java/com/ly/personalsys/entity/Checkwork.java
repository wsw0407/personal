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
 * @since 2021-03-26
 */
@Data
@TableName(value = "checkwork_inf")
@EqualsAndHashCode(callSuper = false)
public class Checkwork extends Model<Checkwork> {

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

    @TableField("WORKINGDAYS")
    private Integer workingdays;

    @TableField("DAYSLEAVE")
    private Integer daysleave;

    @TableField("CREATEDATE")
    private Date createdate;

    @TableField("DAYSOUT")
    private Integer daysout;

    public String getCreatTimeStr(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (this.createdate != null){
            return sdf.format(this.createdate);
        }else {
            return  null;
        }
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
