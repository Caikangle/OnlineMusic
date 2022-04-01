package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.SongList;
import com.hust.music.service.SongListService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    @GetMapping("allSongList")
    public Object getAllSongList(){
        return songListService.allSongList();
    }

    @PostMapping("/add")
    public Object addSongList(HttpServletRequest request){
        String title = request.getParameter("title").trim();
        String pic = request.getParameter("pic").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();
        SongList songList = new SongList();
        songList.setIntroduction(introduction);
        songList.setPic(pic);
        songList.setStyle(style);
        songList.setTitle(title);
        JSONObject jsonObject =new JSONObject();
        boolean insert = songListService.insert(songList);
        if (insert) {
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"歌单添加成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"歌单添加失败");
        return jsonObject;
    }

    @PostMapping("/update")
    public Object updateSongList(HttpServletRequest request){
        String id = request.getParameter("id").trim();
        String title = request.getParameter("title").trim();
        String introduction = request.getParameter("introduction").trim();
        String style = request.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        JSONObject jsonObject =new JSONObject();
        boolean update = songListService.update(songList);
        if (update) {
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"歌单更新成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"歌单更新失败");
        return jsonObject;
    }

    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("id") Integer id,@RequestParam("file") MultipartFile file){
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        }

        String uploadPath = System.getProperty("user.dir") +
                System.getProperty("file.separator") +
                "img" +
                System.getProperty("file.separator") +
                "songListPic"
                ;
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        String relativePath = "/img/songListPic/" + fileName;
        try {
            file.transferTo(new File(uploadPath,fileName));
            SongList songList = new SongList();
            songList.setPic(relativePath);
            songList.setId(id);
            boolean update = songListService.update(songList);
            if (update) {
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG,"上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG,"上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败："+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam("id") Integer id){
        boolean delete = songListService.delete(id);
        return delete;
    }

    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(@RequestParam("id") Integer id){
        return songListService.selectByPrimaryKey(id);
    }

    /**
     * 根据歌单标题精准查询歌单
     * @param title
     * @return
     */
    @GetMapping("/songListOfTitle")
    public Object songListOfTitle(@RequestParam("title") String title){
        return songListService.songListOfTitle(title);
    }

    /**
     * 根据歌单标题模糊查询歌单
     * @param title
     * @return
     */
    @GetMapping("/likeTitle")
    public Object likeTitle(@RequestParam("title") String title){
        return songListService.likeTitle("%"+title+"%");
    }

    /**
     * 根据歌单风格模糊查询歌单
     * @param style
     * @return
     */
    @GetMapping("/likeStyle")
    public Object likeStyle(@RequestParam("style") String style){
        return songListService.likeStyle("%"+style+"%");
    }
}
