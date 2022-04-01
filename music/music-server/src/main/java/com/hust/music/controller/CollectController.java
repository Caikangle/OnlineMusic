package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Collect;
import com.hust.music.service.CollectService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @GetMapping("/collectOfUserId")
    public Object collectOfUserId(HttpServletRequest request){
        String userId = request.getParameter("userId");
        return collectService.collectOfUserId(Integer.parseInt(userId));
    }

    @GetMapping("/delete")
    public Object delete(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String songId = request.getParameter("songId");
        return collectService.delete(Integer.parseInt(userId),Integer.parseInt(songId));
    }

    @PostMapping("/add")
    public Object add(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
//        System.out.println("songId:"+songId);
        boolean isExist = collectService.getCollectByUserIdAndSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        if (isExist) {
            jsonObject.put(Consts.CODE,2);
            return jsonObject;
        }
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(Byte.parseByte(type));
        collect.setSongId(Integer.parseInt(songId));
        boolean add = collectService.add(collect);
        if (add) {
            jsonObject.put(Consts.CODE, 1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        return jsonObject;
    }
}
