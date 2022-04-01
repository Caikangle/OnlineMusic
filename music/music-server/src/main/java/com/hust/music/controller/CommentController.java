package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Comment;
import com.hust.music.service.CommentService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Object addComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        String type = request.getParameter("type");
        String userId = request.getParameter("userId");
        String content = request.getParameter("content");
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(Byte.valueOf(type));
        if ("0".equals(type)) {
            String songId = request.getParameter("songId");
            comment.setSongId(Integer.parseInt(songId));
        }else{
            String songListId = request.getParameter("songListId");
            comment.setSongListId(Integer.parseInt(songListId));
        }
        boolean insert = commentService.insert(comment);
        if (insert) {
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        return jsonObject;
    }

    @PostMapping("/like")
    public Object setLike(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String id = request.getParameter("id");
        String up = request.getParameter("up");
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));
        boolean update = commentService.update(comment);
        if (update) {
            jsonObject.put(Consts.CODE, 1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        return jsonObject;
    }

    @GetMapping("/commentOfSongId")
    public Object commentOfSongId(HttpServletRequest request){
        String songId = request.getParameter("songId");
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    @GetMapping("/commentOfSongListId")
    public Object commentOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    @GetMapping("/delete")
    public Object delete(HttpServletRequest request){
        String id = request.getParameter("id");
        return commentService.delete(Integer.parseInt(id));
    }
}
