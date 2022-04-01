package com.hust.music.dao;

import com.hust.music.domain.Rank;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价dao
 */
@Mapper
public interface RankMapper {
    /**
     *增加
     */
    public int insert(Rank rank);

    /**
     * 查总分
     */
    public int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    public int selectRankNum(Integer songListId);

    int getRank(Integer songListId, Integer consumerId);
}
