/**
 * 版权： 北京联众信安成都分公司
 * 描述： dao层接口
 * 创建时间：2018年08月08日
 */
package com.hachuizi.service.dao;

import com.hachuizi.service.entity.UserEntity;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * 〈简述〉dao层接口.
 *
 * @author HHYang
 * @version [版本号, 2018年08月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IUserDao {

    List<UserEntity> findByPage(Map<String, Object> map);

    UserEntity findById(String id);

    int insert(UserEntity obj);

    int update(UserEntity obj);

    int delete(String id);
}