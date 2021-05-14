package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
@Data
@TableName(value = "education_inf")
@EqualsAndHashCode(callSuper = false)
public class Education extends Model<Education> {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
