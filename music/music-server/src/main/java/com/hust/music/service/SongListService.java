package com.hust.music.service;

import com.hust.music.domain.SongList;

import java.util.List;

public interface SongListService {
    /**
     * 添加歌单
     * @param songList
     * @return
     */
    boolean insert(SongList songList);

    /**
     * 更新歌单
     * @param songList
     * @return
     */
    boolean update(SongList songList);

    /**
     * 根据id删除歌单
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询歌单
     * @param id
     * @return
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     * @return
     */
    List<SongList> allSongList();

    /**
     * 根据标题精准查询歌单
     * @param title
     * @return
     */
    SongList songListOfTitle(String title);

    /**
     * 根据风格查询歌单
     * @param style
     * @return
     */
    List<SongList> likeStyle(String style);

    /**
     * 根据标题查询歌单
     * @param title
     * @return
     */
    List<SongList> likeTitle(String title);
}
