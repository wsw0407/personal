package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2021-03-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SexInf extends Model<SexInf> {

    private static final long serialVersionUID=1L;

      private Integer id;

    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
