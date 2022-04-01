package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Consumer;
import com.hust.music.service.ConsumerService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    /**
     * 1.添加用户
     * 2.修改用户
     * 3.更新用户头像
     * 4.删除用户
     */


    @Autowired
    private ConsumerService consumerService;

    /**
     * 用户登录
     * @param request
     * @return
     */
    @PostMapping("/login")
    public Object login(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if (username == "" || username.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (password == "" || password.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        Consumer consumer = consumerService.selectByUsernameAndPassword(username, password);
        if (consumer != null){
            jsonObject.put(Consts.CODE,1);
            jsonObject.put(Consts.MSG, "登录成功");
            jsonObject.put("userMsg",consumer);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, "0");
        jsonObject.put(Consts.MSG, "用户名或密码错误");
        return jsonObject;
    }

    @PostMapping("/add")
    public Object addConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        String avator = request.getParameter("avator").trim();
        if (username == "" || username.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        Consumer consumer1 = consumerService.selectByExactName(username);
        if (consumer1 != null) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "用户名已存在");
            return jsonObject;
        }
        if (password == "" || password.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.valueOf(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss"
        );
        try {
            date = sdf.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setBirth(date);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        boolean insert = consumerService.insert(consumer);
        if (insert) {
            jsonObject.put(Consts.CODE, "1");
            jsonObject.put(Consts.MSG, "添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, "0");
        jsonObject.put(Consts.MSG, "添加失败");
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updateConsumer(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String sex = request.getParameter("sex").trim();
        String phoneNum = request.getParameter("phoneNum").trim();
        String email = request.getParameter("email").trim();
        String birth = request.getParameter("birth").trim();
        String introduction = request.getParameter("introduction").trim();
        String location = request.getParameter("location").trim();
        if (username == "" || username.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "用户名不能为空");
            return jsonObject;
        }
        if (password == "" || password.equals("")) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "密码不能为空");
            return jsonObject;
        }
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(Byte.valueOf(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd"
        );
        try {
            date = sdf.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setBirth(date);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        boolean update = consumerService.update(consumer);
        if (update) {
            jsonObject.put(Consts.CODE, "1");
            jsonObject.put(Consts.MSG, "修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE, "0");
        jsonObject.put(Consts.MSG, "修改失败");
        return jsonObject;
    }

    @GetMapping("/allConsumer")
    public Object getAllConsumer() {
        return consumerService.selectAllConsumers();
    }

    @GetMapping("/delete")
    public Object delConsumer(@RequestParam("id") Integer id) {
        return consumerService.delete(id);
    }

    @GetMapping("/selectByPrimaryKey")
    public Object getConsumerOfId(@RequestParam("id") Integer id) {
        return consumerService.selectByPrimaryKey(id);
    }

    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("id") Integer id, @RequestParam("file") MultipartFile file) {
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "文件为空，上传失败");
            return jsonObject;
        }
        String uploadPath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "avatorImages";
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath, fileName));
            Consumer consumer = new Consumer();
            consumer.setId(id);
            String avaPath = "/avatorImages/" + fileName;
            consumer.setAvator(avaPath);
            boolean update = consumerService.update(consumer);
            if (update) {
                jsonObject.put(Consts.CODE, "1");
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avator",avaPath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败：" + e.getMessage());
        } finally {
            return jsonObject;
        }
    }
}
