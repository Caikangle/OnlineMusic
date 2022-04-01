package com.hust.music.service.impl;

import com.hust.music.dao.ListSongMapper;
import com.hust.music.domain.ListSong;
import com.hust.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;
    /**
     * 添加歌单里的歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    /**
     * 更新歌单里的歌曲
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong) > 0;
    }

    /**
     * 根据id删除歌单里的歌曲
     *
     * @param songId
     * @param songListId
     * @return
     */
    @Override
    public boolean delete(Integer songId, Integer songListId) {
        return listSongMapper.delete(songId,songListId) > 0;
    }

    /**
     * 根据主键查询歌单里的某一歌曲
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据歌单id查询所有的歌曲
     *
     * @param songListId
     * @return
     */
    @Override
    public List<ListSong> listSongOfSongListId(Integer songListId) {
        return listSongMapper.listSongOfSongListId(songListId);
    }

    /**
     * 查询所有的歌单里面的歌曲
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }
}
