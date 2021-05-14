package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserInf extends Model<UserInf> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String loginname;

    private String password;

    private String email;

    private Integer statusId;

    /**
     * 创建时间
     */
    private Date createdate;

    private String username;

    private Integer empId;

    private Integer cheId;

    private Integer conId;

    private Integer salId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
