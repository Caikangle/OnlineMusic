package com.hust.music.dao;

import com.hust.music.domain.Collect;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CollectMapper {

    /**
     * 添加收藏
     * @param collect
     * @return
     */
    int add(Collect collect);

    /**
     * 根据用户id查询收藏信息
     * @param userId
     * @return
     */
    List<Collect> collectOfUserId(Integer userId);

    int getCollectByUserIdAndSongId(int userId, int songId);

    int delete(int userId, int songId);
}
