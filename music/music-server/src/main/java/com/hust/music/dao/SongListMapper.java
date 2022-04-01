package com.hust.music.dao;

import com.hust.music.domain.SongList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SongListMapper {
    /**
     * 添加歌单
     * @param songList
     * @return
     */
    int insert(SongList songList);

    /**
     * 更新歌单
     * @param songList
     * @return
     */
    int update(SongList songList);

    /**
     * 根据id删除歌单
     * @param id
     * @return
     */
    int delete(Integer id);

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
