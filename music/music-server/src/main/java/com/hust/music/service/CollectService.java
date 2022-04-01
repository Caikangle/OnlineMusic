package com.hust.music.service;

import com.hust.music.domain.Collect;

import java.util.List;

public interface CollectService {
    /**
     * 添加收藏
     * @param collect
     * @return
     */
    boolean add(Collect collect);

    /**
     * 根据用户id查询收藏信息
     * @param userId
     * @return
     */
    List<Collect> collectOfUserId(Integer userId);

    /**
     * 根据用户id和歌曲id查询收藏列表
     * @param userId
     * @param songId
     * @return
     */
    boolean getCollectByUserIdAndSongId(int userId, int songId);

    boolean delete(int userId, int songId);
}
