package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-23
 */
@Data
@TableName( value = "uservisit_inf")
@EqualsAndHashCode(callSuper = false)
public class Uservisit extends Model<Uservisit> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String loginTime;

    private String exitTime;

    private String visitIp;

    private String userAddress;

    private String userFrom;

    private String browser;

    private String system;

    private String version;

    private String loginname;

    private String iphone;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
