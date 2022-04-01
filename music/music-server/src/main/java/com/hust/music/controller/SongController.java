package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Song;
import com.hust.music.service.SongService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    private SongService songService;

    /**
     * 查询所有的歌曲
     * @return
     */
    @GetMapping("/allSong")
    public Object getAllSong() {
        List<Song> songs = songService.allSong();
        return songs;
    }

    /**
     * 添加歌曲
     * @param request
     * @param file 歌曲mp3文件
     * @param file1 歌曲mv文件
     * @return
     */
    @PostMapping("/add")
    public Object addSong(HttpServletRequest request,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("files") MultipartFile file1){
        JSONObject jsonObject = new JSONObject();
//        获取前端的参数
        String singerId = request.getParameter("singerId").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = request.getParameter("lyric").trim();
        //上传歌曲文件
        //歌曲为空
        if (file == null ||file.isEmpty()) {
            jsonObject.put(Consts.CODE,0);
            jsonObject.put(Consts.MSG, "歌曲文件为空");
            return jsonObject;
        }
        //定义上传路径
        String uploadPath = System.getProperty("user.dir")+
                System.getProperty("file.separator")+
                "song";
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //文件名
        String filename = file.getOriginalFilename();
        try {
            file.transferTo(new File(uploadPath,filename));
            //路径存入数据库
            String relativePath = "/song/"+filename;
            Song song = new Song();
            song.setUrl(relativePath);
            song.setName(name);
            song.setIntroduction(introduction);
            song.setLyric(lyric);
//            song.setMvurl(mvurl);
            song.setPic(pic);
            song.setSingerId(Integer.parseInt(singerId));
            if (!file1.isEmpty()) { //有mv
                String mvUploadPath = System.getProperty("user.dir") +
                        System.getProperty("file.separator")+
                        "mv";
                String mvFileName = System.currentTimeMillis() + file1.getOriginalFilename();
                File path = new File(mvUploadPath);
                if (!path.exists()) {
                    path.mkdir();
                }
                file1.transferTo(new File(mvUploadPath,mvFileName));
                String mvRelativePath = "/mv/"+mvFileName;
                song.setMvurl(mvRelativePath);
            }
            boolean insert = songService.insert(song);
            if (insert) {
                jsonObject.put(Consts.CODE,"1");
                jsonObject.put(Consts.MSG, "上传成功");
                jsonObject.put("avator",relativePath);
                return jsonObject;
            }
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE,"0");
            jsonObject.put(Consts.MSG, "上传失败"+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    /**
     * 根据歌手id查询歌曲
     * @param singerId
     * @return
     */
    @GetMapping("/singer/detail")
    public Object selectSongsBySingerId(@RequestParam("singerId") Integer singerId){
        List<Song> songs = songService.songOfSingerId(singerId);
        return songs;
    }

    /**
     * 更新歌曲基本信息
     * @param request
     * @return
     */
    @PostMapping("/update")
    public Object updateSongById(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id").trim();
        String name = request.getParameter("name").trim();
        String introduction = request.getParameter("introduction").trim();
        String lyric = request.getParameter("lyric").trim();
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        boolean update = songService.update(song);
        if (update){
            jsonObject.put(Consts.CODE,"1");
            jsonObject.put(Consts.MSG,"修改成功");
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,"0");
        jsonObject.put(Consts.MSG,"修改失败");
        return jsonObject;
    }

    /**
     * 更新歌曲图片
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/updateSongPic")
    public Object updateSongPicById(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String id = request.getParameter("id").trim();
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "文件为空，上传失败");
            return jsonObject;
        }
        //上传路径
        String uploadPath = System.getProperty("user.dir")+
                System.getProperty("file.separator")+
                "img"+
                System.getProperty("file.separator")+
                "songPic";
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(new File(uploadPath,fileName));
            String relativePath = "/img/songPic/" + fileName;
            Song song = new Song();
            song.setId(Integer.parseInt(id));
            song.setPic(relativePath);
            boolean update = songService.update(song);
            if (update) {
                jsonObject.put(Consts.CODE, "1");
                jsonObject.put(Consts.MSG, "上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败："+e.getMessage());
        }finally {
            return jsonObject;
        }
    }


    /**
     * 更新歌曲mp3
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/updateSongUrl")
    public Object updateSongUrlById(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String id = request.getParameter("id").trim();
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "文件为空，上传失败");
            return jsonObject;
        }
        //上传路径
        String uploadPath = System.getProperty("user.dir")+
                System.getProperty("file.separator")+
                "song";
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(new File(uploadPath,fileName));
            String relativePath = "/song/" + fileName;
            Song song = new Song();
            song.setId(Integer.parseInt(id));
            song.setUrl(relativePath);
            boolean update = songService.update(song);
            if (update) {
                jsonObject.put(Consts.CODE, "1");
                jsonObject.put(Consts.MSG, "上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败："+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    /**
     * 更新歌曲mv
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/updateMVUrl")
    public Object updateSongMvUrlById(HttpServletRequest request,@RequestParam("file") MultipartFile file){
        String id = request.getParameter("id").trim();
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "文件为空，上传失败");
            return jsonObject;
        }
        //上传路径
        String uploadPath = System.getProperty("user.dir")+
                System.getProperty("file.separator")+
                "mv";
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            file.transferTo(new File(uploadPath,fileName));
            String relativePath = "/mv/" + fileName;
            Song song = new Song();
            song.setId(Integer.parseInt(id));
            song.setMvurl(relativePath);
            boolean update = songService.update(song);
            if (update) {
                jsonObject.put(Consts.CODE, "1");
                jsonObject.put(Consts.MSG, "上传成功");
                return jsonObject;
            }
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败");
            return jsonObject;
        } catch (Exception e) {
            jsonObject.put(Consts.CODE, "0");
            jsonObject.put(Consts.MSG, "上传失败："+e.getMessage());
        }finally {
            return jsonObject;
        }
    }

    @GetMapping("/delete")
    public boolean deleteSongById(@RequestParam("id") Integer id){
        return songService.delete(id);
    }

    /**
     * 根据歌曲id查询歌曲对象
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object getSongById(@RequestParam("songId") Integer id){
        return songService.selectByPrimaryKey(id);
    }

    /**
     * 根据歌曲名查询歌曲对象
     * @param songName
     * @return
     */
    @GetMapping("/songOfSongName")
    public Object getSongByName(@RequestParam("songName") String songName){
        Song song = songService.songOfName(songName);
        return song;
    }

    /**
     * 根据歌曲名模糊查询歌曲对象
     * @param songName
     * @return
     */
    @GetMapping("/likeSongOfName")
    public Object likeSongOfName(@RequestParam("songName") String songName){
        return songService.likeSongOfName("%"+songName+"%");
    }


}
