package com.hust.music.service;

import com.hust.music.domain.ListSong;

import java.util.List;

public interface ListSongService {

    /**
     * 添加歌单里的歌曲
     * @param listSong
     * @return
     */
    boolean insert(ListSong listSong);

    /**
     * 更新歌单里的歌曲
     * @param listSong
     * @return
     */
    boolean update(ListSong listSong);

    /**
     * 根据id删除歌单里的歌曲
     * @param songId
     * @param songListId
     * @return
     */
    boolean delete(Integer songId, Integer songListId);

    /**
     * 根据主键查询歌单里的某一歌曲
     * @param id
     * @return
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 根据歌单id查询所有的歌曲
     * @param songListId
     * @return
     */
    List<ListSong> listSongOfSongListId(Integer songListId);

    /**
     * 查询所有的歌单里面的歌曲
     * @return
     */
    List<ListSong> allListSong();
}
