package com.ly.personalsys.vo;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data

public class EmployeeVo implements Serializable {

    private static final long serialVersionUID=1L;


    private Integer id;

    private Integer deptId;

    private String deptname;

    private Integer jobId;

    private String jobname;

    private String name;

    private String cardId;

    private String address;

    private String phone;

    private Integer sexId;

    private Integer educationId;

    private String educationname;

    /**
     * 创建时间
     */
    private Date createdate;

    private Integer userId;

    private  String loginname;

    private  String email;

    public String getCreatTimeStr(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (this.createdate != null){
            return sdf.format(this.createdate);
        }else {
            return  null;
        }
    }

}
