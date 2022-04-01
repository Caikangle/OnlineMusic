package com.hust.music.service;

import com.hust.music.domain.Consumer;

import java.util.List;

public interface ConsumerService {

    /**
     * 添加一名用户
     * @param consumer
     * @return
     */
    boolean insert(Consumer consumer);

    /**
     * 修改用户
     * @param consumer
     * @return
     */
    boolean update(Consumer consumer);

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据主键ID查询用户
     * @param id
     * @return
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<Consumer> selectAllConsumers();

    /**
     * 根据用户名模糊查询
     * @param username
     * @return
     */
    List<Consumer> selectByName(String username);

    Consumer selectByExactName(String username);

    /**
     * 根据用户名与密码查询用户
     * @param username
     * @param password
     * @return
     */
    Consumer selectByUsernameAndPassword(String username,String password);
}
