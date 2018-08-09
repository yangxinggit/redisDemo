/**
 * 版权： 北京联众信安成都分公司
 * 描述： 用户处理业务层
 * 创建时间：2018年08月08日
 */
package com.hachuizi.service;

import com.alibaba.fastjson.JSONObject;
import com.hachuizi.service.dao.IUserDao;
import com.hachuizi.service.entity.UserEntity;
import com.hachuizi.util.RedisUtil;
import com.owinfo.utils.basetools.OwinfoResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 〈简述〉用户处理业务层--配置redis实现
 *
 * @author HHYang
 * @version [版本号, 2018年08月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private RedisUtil redisUtil;

    public JSONObject findByPage(Map<String, Object> map) {
        String key = "susers";
        if (redisUtil.keyIsExist(key)) {
            List<Object> objs = redisUtil.getCacheList(key);
            return OwinfoResultUtil.success(objs, "success");
        } else {
            List<UserEntity> objs = userDao.findByPage(map);
            redisUtil.putCacheList(key, objs, Long.valueOf(30));
            return OwinfoResultUtil.success(objs, "success");
        }
    }

    public JSONObject findById(String id) {
        String key = "user_" + id;
        if (redisUtil.keyIsExist(key)) {
            Object obj = redisUtil.getCacheSimple(key);
            return OwinfoResultUtil.success(obj, "success");
        }
        UserEntity obj = userDao.findById(id);
        redisUtil.putCacheSimple(key, obj, Long.valueOf(30));
        return OwinfoResultUtil.success(obj, "success");
    }

    public JSONObject insert(UserEntity obj) {
        String id = UUID.randomUUID().toString().replaceAll("-", "");
        String key = "user_" + id;
        obj.setId(id);
        obj.setCreateTime(new Date());
        int res = userDao.insert(obj);
        if (res > 0)
            redisUtil.putCacheSimple(key, obj, Long.valueOf(30));
        return OwinfoResultUtil.success(obj, "success");
    }

    public JSONObject update(UserEntity obj) {
        int res = userDao.update(obj);
        return OwinfoResultUtil.success(res, "success");
    }

    public JSONObject delete(String id) {
        String key = "user_" + id;
        redisUtil.cleanRedisByKey(key);
        int res = userDao.delete(id);
        return OwinfoResultUtil.success(res, "success");
    }
}