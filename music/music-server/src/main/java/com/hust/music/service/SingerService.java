package com.hust.music.service;

import com.hust.music.domain.Singer;

import java.util.List;

/**
 * 歌手Service层的接口
 */
public interface SingerService {
    /**
     * 增加一个歌手
     * @param singer
     * @return
     */
    public boolean insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    public boolean update(Singer singer);

    /**
     * 根据id删除歌手
     * @param id
     * @return
     */
    public boolean delete(Integer id);

    /**
     * 根据主键id查找歌手
     * @param id
     * @return
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有的歌手信息
     * @return
     */
    public List<Singer> selectAllSingers();

    /**
     * 根据名字模糊查询
     * @param name
     * @return
     */
    public List<Singer> selectByName(String name);

    /**
     * 根据性别查询
     * @param sex
     * @return
     */
    public List<Singer> selectBySex(int sex);

}
