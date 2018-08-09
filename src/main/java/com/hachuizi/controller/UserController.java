/**
 * 版权： 北京联众信安成都分公司
 * 描述： 用户控制器层
 * 创建时间：2018年08月08日
 */
package com.hachuizi.controller;

import com.alibaba.fastjson.JSONObject;
import com.hachuizi.service.UserService;
import com.hachuizi.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 〈简述〉用户控制器层.
 *
 * @author HHYang
 * @version [版本号, 2018年08月08日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /*查询所有*/
    @PostMapping("findByPage")
    public JSONObject findByPage(@RequestBody Map<String, Object> map) {
        JSONObject jb = userService.findByPage(map);
        return jb;
    }

    @GetMapping("findById/{id}")
    public JSONObject findById(@PathVariable String id) {
        JSONObject jb = userService.findById(id);
        return jb;
    }

    @PostMapping("insert")
    public JSONObject insert(@RequestBody UserEntity obj) {
        JSONObject jb = userService.insert(obj);
        return jb;
    }

    @PostMapping("update")
    public JSONObject update(@RequestBody UserEntity obj) {
        JSONObject jb = userService.update(obj);
        return jb;
    }

    @GetMapping("delete/{id}")
    public JSONObject delete(@PathVariable String id) {
        JSONObject jb = userService.delete(id);
        return jb;
    }
} 