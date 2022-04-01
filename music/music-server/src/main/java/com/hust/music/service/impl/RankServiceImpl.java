package com.hust.music.service.impl;

import com.hust.music.dao.RankMapper;
import com.hust.music.domain.Rank;
import com.hust.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * 查询评价记录
     *
     * @param songListId
     * @param consumerId
     * @return
     */
    @Override
    public boolean getRank(Integer songListId, Integer consumerId) {
        return rankMapper.getRank(songListId, consumerId)>0;
    }

    /**
     * 增加
     *
     * @param rank
     */
    @Override
    public boolean insert(Rank rank) {
        return rankMapper.insert(rank)>0;
    }

    /**
     * 查总分
     *
     * @param songListId
     */
    @Override
    public int selectScoreSum(Integer songListId) {
        return rankMapper.selectScoreSum(songListId);
    }

    /**
     * 查总评分人数
     *
     * @param songListId
     */
    @Override
    public int selectRankNum(Integer songListId) {
        return rankMapper.selectRankNum(songListId);
    }

    /**
     * 计算平均分
     *
     * @param songListId
     */
    @Override
    public int rankOfSongListId(Integer songListId) {
        int totalScore = rankMapper.selectScoreSum(songListId);
        int totalNum = rankMapper.selectRankNum(songListId);
        return totalScore/totalNum;
    }
}
