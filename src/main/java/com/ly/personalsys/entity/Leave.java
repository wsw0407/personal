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
@TableName(value = "leave_inf")
@EqualsAndHashCode(callSuper = false)
public class Leave extends Model<Leave> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer empId;

    @TableField(exist = false)
    private String empname;

    private Integer deptId;

    @TableField(exist = false)
    private String deptname;

    private Integer jobId;

    @TableField(exist = false)
    private String jobname;

    private String startdata;

    private String enddata;

    private String leavedays;

    private Integer leavetype;

    @TableField(exist = false)
    private String leavetname;

    private String content;

    private Integer leavestatus;

    @TableField(exist = false)
    private String statusname;

    /**
     * 创建时间
     */
    private Date leavetime;

    public String getCreatTimeStr(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (this.leavetime != null){
            return sdf.format(this.leavetime);
        }else {
            return  null;
        }
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
