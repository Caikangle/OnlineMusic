package com.hust.music.dao;

import com.hust.music.domain.Singer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 歌手dao
 */
@Mapper
public interface SingerMapper {

    /**
     * 增加一个歌手
     * @param singer
     * @return
     */
    public int insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    public int update(Singer singer);

    /**
     * 根据id删除歌手
     * @param id
     * @return
     */
    public int delete(Integer id);

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
    List<Singer> selectBySex(Integer sex);
}
