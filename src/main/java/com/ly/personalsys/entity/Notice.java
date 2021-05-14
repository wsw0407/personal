package com.ly.personalsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-03-25
 */
@Data
@TableName(value = "notice_inf")
@EqualsAndHashCode(callSuper = false)
public class Notice extends Model<Notice> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    /**
     * 创建时间
     */
    private Date createdate;

    private Integer userId;

    private String username;

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
