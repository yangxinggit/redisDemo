/**
 * 版权： 北京联众信安成都分公司
 * 描述： 用户处理业务层
 * 创建时间：2018年08月08日
 */
package com.hachuizi.service;

import com.alibaba.fastjson.JSONObject;
import com.hachuizi.service.dao.IUserDao;
import com.hachuizi.service.entity.UserEntity;
import com.owinfo.utils.basetools.OwinfoResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 〈简述〉用户处理业务层-- 注解方式实现
 *
 * @author HHYang
 * @version [版本号, 2018年08月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserService2 {

    @Autowired
    private IUserDao userDao;

    @Cacheable(value = "susers")
    public JSONObject findByPage(Map<String, Object> map) {
        List<UserEntity> objs = userDao.findByPage(map);
        return OwinfoResultUtil.success(objs, "success");
    }

    @Cacheable(value = "user", key = "'user_'+#id")
    public JSONObject findById(String id) {
        UserEntity obj = userDao.findById(id);
        return OwinfoResultUtil.success(obj, "success");
    }

    @CachePut(value = "user", key = "'user_' + #result.get('data').getId()")
    public JSONObject insert(UserEntity obj) {
        obj.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        obj.setCreateTime(new Date());
        int res = userDao.insert(obj);
        return OwinfoResultUtil.success(obj, "success");
    }

    public JSONObject update(UserEntity obj) {
        int res = userDao.update(obj);
        return OwinfoResultUtil.success(res, "success");
    }

    @CacheEvict(value = "users", key = "'user_'+#id", condition = "#id!=1")
    public JSONObject delete(String id) {
        int res = userDao.delete(id);
        return OwinfoResultUtil.success(res, "success");
    }
}