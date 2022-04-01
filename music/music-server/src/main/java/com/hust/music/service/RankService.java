package com.hust.music.service;

import com.hust.music.domain.Rank;

/**
 * 评分的service
 */
public interface RankService {

    /**
     * 查询评价记录
     * @param songListId
     * @param consumerId
     * @return
     */
    public boolean getRank(Integer songListId,Integer consumerId);

    /**
     *增加
     */
    public boolean insert(Rank rank);

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);

    /**
     * 计算平均分
     */
    public int rankOfSongListId(Integer songListId);
}
