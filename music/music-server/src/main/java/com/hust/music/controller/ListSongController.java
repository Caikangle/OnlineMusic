package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.ListSong;
import com.hust.music.service.ListSongService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("listSong")
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @GetMapping("detail")
    public Object listSongDetail(HttpServletRequest request){
        String songListId = request.getParameter("songListId").trim();
//        System.out.println("songListId:"+songListId);
        List<ListSong> listSongs = listSongService.listSongOfSongListId(Integer.parseInt(songListId));
        for (ListSong listSong : listSongs) {
            System.out.println(listSong.getSongId());
        }
        return listSongs;
    }

    @PostMapping("/add")
    public Object listSongAdd(HttpServletRequest request){
        String songId = request.getParameter("songId").trim();
        String songListId = request.getParameter("songListId").trim();
        ListSong listSong = new ListSong();
//        System.out.println("songId:"+songId);
//        System.out.println("songListId:"+songListId);
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        JSONObject jsonObject = new JSONObject();
        boolean insert = listSongService.insert(listSong);
        if (insert) {
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"添加失败");
        return jsonObject;
    }

    /**
     * 删除歌单里面的歌曲
     * @param songId
     * @param songListId
     * @return
     */
    @GetMapping("/delete")
    public Object delete(@RequestParam("songId") Integer songId,
                         @RequestParam("songListId") Integer songListId){
        return listSongService.delete(songId, songListId);
    }
}
