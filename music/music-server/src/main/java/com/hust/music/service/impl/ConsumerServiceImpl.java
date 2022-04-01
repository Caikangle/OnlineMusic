package com.hust.music.service.impl;

import com.hust.music.dao.ConsumerMapper;
import com.hust.music.domain.Consumer;
import com.hust.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;
    /**
     * 添加一名用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer)>0;
    }

    /**
     * 修改用户
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer) > 0;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id) > 0;
    }

    /**
     * 根据主键ID查询用户
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有用户
     *
     * @return
     */
    @Override
    public List<Consumer> selectAllConsumers() {
        return consumerMapper.selectAllConsumers();
    }

    /**
     * 根据用户名模糊查询
     *
     * @param username
     * @return
     */
    @Override
    public List<Consumer> selectByName(String username) {
        return consumerMapper.selectByName(username);
    }

    @Override
    public Consumer selectByExactName(String username) {
        return consumerMapper.selectByExactName(username);
    }

    /**
     * 根据用户名与密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Consumer selectByUsernameAndPassword(String username, String password) {
        return consumerMapper.selectByUsernameAndPassword(username, password);
    }
}
