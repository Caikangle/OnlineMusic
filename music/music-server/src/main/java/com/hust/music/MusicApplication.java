package com.hust.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 热更新+热加载
 * 1.Ctrl + shift + A 找到Registry 找到cmpliler....when.app.running
 * 2.Ctrl + F9 执行快捷键执行热加载
 */
@MapperScan(value = "com.hust.music.dao")
@SpringBootApplication
public class MusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

}
