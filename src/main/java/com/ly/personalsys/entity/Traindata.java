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
 * @since 2021-03-29
 */
@Data
@TableName(value = "traindata_inf")
@EqualsAndHashCode(callSuper = false)
public class Traindata extends Model<Traindata> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private String filename;

    private String remark;

    /**
     * 创建时间
     */
    private Date createdate;

    private Integer userId;

    @TableField(exist = false)
    private String username;

    public String getCreatTimeStr(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (this.createdate != null){
            return sdf.format(this.createdate);
        }else {
            return  null;
        }
    }

    public String getFileStr(){

        return spiltFileName(this.filename);
    }


    public String spiltFileName(String fileName) {

        Integer num1 = fileName.lastIndexOf('_');
        if(num1 < 0){
            return fileName;
        }
        String fileprefix = fileName.substring(0,num1);

        Integer num2 = fileName.lastIndexOf('.');
        String filesuffix = fileName.substring(num2);

        fileName=fileprefix+filesuffix;
        System.err.println("========fileName========"+fileName);

        return fileName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
