package com.hust.music.service;

import com.hust.music.domain.Song;

import java.util.List;

public interface SongService {
    /**
     * 增加一个歌手
     * @param song
     * @return
     */
    public boolean insert(Song song);

    /**
     * 修改
     * @param song
     * @return
     */
    public boolean update(Song song);

    /**
     * 根据id删除歌曲
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键id查找歌曲
     * @param id
     * @return
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有的歌曲
     * @return
     */
    public List<Song> allSong();

    /**
     * 根据歌名精确查询列表
     * @param name
     * @return
     */
    public Song songOfName(String name);

    /**
     * 根据歌名模糊查询列表
     * @param name
     * @return
     */
    public List<Song> likeSongOfName(String name);

    /**
     * 根据歌手id查询歌曲
     * @param singerId
     * @return
     */
    public List<Song> songOfSingerId(Integer singerId);

}
