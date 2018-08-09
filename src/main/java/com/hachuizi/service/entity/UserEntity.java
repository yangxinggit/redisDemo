/**
 * 版权： 北京联众信安成都分公司
 * 描述： 用户实体对象
 * 创建时间：2018年08月08日
 */
package com.hachuizi.service.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 〈简述〉用户实体对象.
 *
 * @author HHYang
 * @version [版本号, 2018年08月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
public class UserEntity implements Serializable {

    protected static final long serialVersionUID = 1L;

    private String id;

    private String userName;

    private String password;

    private Integer userTel;

    private Date createTime;
}