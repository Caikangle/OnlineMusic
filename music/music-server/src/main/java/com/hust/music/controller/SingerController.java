package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Singer;
import com.hust.music.service.SingerService;
import com.hust.music.utils.Consts;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.hust.music.utils.Consts.CODE;
import static com.hust.music.utils.Consts.MSG;

@Log4j2
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @GetMapping("/allSinger")
    public Object getAllSinger() {
        List<Singer> singers = singerService.selectAllSingers();
        return singers;
    }

    @PostMapping("/add")
    public JSONObject addSinger(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthday);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        JSONObject jsonObject = new JSONObject();
        boolean insert = singerService.insert(singer);
        if (insert) {
            jsonObject.put(CODE, 1);
            jsonObject.put(Consts.MSG, "添加成功");
        } else {
            jsonObject.put(CODE, 0);
            jsonObject.put(Consts.MSG, "添加失败");
        }
        return jsonObject;
    }

    @PostMapping("/update")
    public JSONObject updateSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String sex = request.getParameter("sex").trim();
//        String pic = request.getParameter("pic").trim();
        String birth = request.getParameter("birth").trim();
        String location = request.getParameter("location").trim();
        String introduction = request.getParameter("introduction").trim();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = new Date();
        try {
            birthday = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
//        singer.setPic(pic);
        singer.setBirth(birthday);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean update = singerService.update(singer);
        JSONObject jsonObject = new JSONObject();
        if (update) {
            jsonObject.put(CODE, 1);
            jsonObject.put(Consts.MSG, "修改成功");
        } else {
            jsonObject.put(CODE, 0);
            jsonObject.put(Consts.MSG, "修改失败");
        }
        return jsonObject;
    }

    @GetMapping("/delete")
    public Object delSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        boolean delete = singerService.delete(Integer.parseInt(id));
        return delete;
    }

    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();
        return singerService.selectByPrimaryKey(Integer.parseInt(id));
    }

    @GetMapping("/selectByName")
    public Object selectByName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();
        List<Singer> singers = singerService.selectByName("%" + name + "%");
        return singers;
    }

    @GetMapping("/singerOfSex")
    public Object selectBySex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim();
        List<Singer> singers = singerService.selectBySex(Integer.parseInt(sex));
        return singers;
    }

    /**
     * 更新歌手图片
     * @param file
     * @param id
     * @return
     */
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        JSONObject jsonObject = new JSONObject();
        //文件为空
        if (file.isEmpty()) {
            jsonObject.put(CODE,"0");
            jsonObject.put(MSG,"上传文件失败");
            return jsonObject;
        }
        log.info("11");
        //文件不为空
        //定义文件名
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        //定义文件的存储路径
        String filePath = System.getProperty("user.dir") +
                System.getProperty("file.separator") + "img"
                +System.getProperty("file.separator") + "singerPic";
        File path = new File(filePath);
        //路径不存在，创建路径
        if (!path.exists()) {
            path.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        try {
            //文件上传
            file.transferTo(dest);
            //将路径存到数据库
            Singer singer = new Singer();
            String relativeUrl = "/img/singerPic/"+fileName;
            singer.setId(id);
            singer.setPic(relativeUrl);
            boolean update = singerService.update(singer);
            if (update) {
                jsonObject.put(CODE,"1");
                jsonObject.put(MSG,"上传文件成功");
                jsonObject.put("pic",relativeUrl);
                return jsonObject;
            }
            jsonObject.put(CODE,"0");
            jsonObject.put(MSG,"上传文件失败");
        } catch (IOException e) {
            jsonObject.put(CODE,"0");
            jsonObject.put(MSG, "上传文件失败  "+e.getMessage());
        }finally {
            return jsonObject;
        }
    }


}
