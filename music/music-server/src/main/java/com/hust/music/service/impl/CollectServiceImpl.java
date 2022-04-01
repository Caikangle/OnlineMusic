package com.hust.music.service.impl;

import com.hust.music.dao.CollectMapper;
import com.hust.music.domain.Collect;
import com.hust.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    @Override
    public boolean add(Collect collect) {
        return collectMapper.add(collect)>0;
    }

    /**
     * 根据用户id查询收藏信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<Collect> collectOfUserId(Integer userId) {
        return collectMapper.collectOfUserId(userId);
    }

    /**
     * 根据用户id和歌曲id查询收藏列表
     *
     * @param userId
     * @param songId
     * @return
     */
    @Override
    public boolean getCollectByUserIdAndSongId(int userId, int songId) {
        return collectMapper.getCollectByUserIdAndSongId(userId, songId) > 0;
    }

    @Override
    public boolean delete(int userId, int songId) {
        return collectMapper.delete(userId, songId) > 0;
    }
}
