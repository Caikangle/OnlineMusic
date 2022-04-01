package com.hust.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.hust.music.domain.Rank;
import com.hust.music.service.RankService;
import com.hust.music.utils.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService rankService;

    /**
     * 获取平均分
     * @param request
     * @return
     */
    @GetMapping("/getRankOfSongListId")
    public Object getRankOfSongListId(HttpServletRequest request){
        String songListId = request.getParameter("songListId");
        return rankService.rankOfSongListId(Integer.parseInt(songListId));
    }

    @PostMapping("/add")
    public Object addRank(HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");
        Rank rank = new Rank();
        rank.setScore(Integer.parseInt(score));
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        boolean rank1 = rankService.getRank(Integer.parseInt(songListId), Integer.parseInt(consumerId));
        if (rank1) {
            jsonObject.put(Consts.CODE,2);
            return jsonObject;
        }
        boolean insert = rankService.insert(rank);
        if (insert){
            jsonObject.put(Consts.CODE,1);
            return jsonObject;
        }
        jsonObject.put(Consts.CODE,0);
        return jsonObject;
    }


}
