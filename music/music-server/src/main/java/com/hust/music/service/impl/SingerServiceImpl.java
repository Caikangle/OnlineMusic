package com.hust.music.service.impl;

import com.hust.music.dao.SingerMapper;
import com.hust.music.domain.Singer;
import com.hust.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 增加一个歌手
     *
     * @param singer
     * @return
     */
    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer)>0;
    }

    /**
     * 修改
     *
     * @param singer
     * @return
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer)>0;
    }

    /**
     * 根据id删除歌手
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id)>0;
    }

    /**
     * 根据主键id查找歌手
     *
     * @param id
     * @return
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有的歌手信息
     *
     * @return
     */
    @Override
    public List<Singer> selectAllSingers() {
        return singerMapper.selectAllSingers();
    }

    /**
     * 根据名字模糊查询
     *
     * @param name
     * @return
     */
    @Override
    public List<Singer> selectByName(String name) {
        return singerMapper.selectByName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     * @return
     */
    @Override
    public List<Singer> selectBySex(int sex) {
        return singerMapper.selectBySex(sex);
    }
}
