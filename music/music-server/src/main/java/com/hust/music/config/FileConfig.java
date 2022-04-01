package com.hust.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Administrator
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //歌单图片地址
        registry.addResourceHandler("/img/songListPic/**")
                .addResourceLocations("file:"
                        //User's current working directory
                        +System.getProperty("user.dir")
                        //File separator ("/" on UNIX)
                        +System.getProperty("file.separator")
                        +"img"
                        +System.getProperty("file.separator")
                        +"songListPic"
                        +System.getProperty("file.separator")
                );
        //歌手头像地址
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations(
                "file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator")+"singerPic"+System.getProperty("file.separator")
        );


        //歌曲图片地址
        registry.addResourceHandler("/img/songPic/**")
                .addResourceLocations("file:"
                        //User's current working directory
                        +System.getProperty("user.dir")
                        //File separator ("/" on UNIX)
                        +System.getProperty("file.separator")
                        +"img"
                        +System.getProperty("file.separator")
                        +"songPic"
                        +System.getProperty("file.separator")
                );
        //歌曲地址
        registry.addResourceHandler("/song/**")
                .addResourceLocations("file:"
                        //User's current working directory
                        +System.getProperty("user.dir")
                        //File separator ("/" on UNIX)
                        +System.getProperty("file.separator")
                        +"song"
                        +System.getProperty("file.separator")
                );
        //歌曲MV地址
        registry.addResourceHandler("/mv/**")
                .addResourceLocations("file:"
                        //User's current working directory
                        +System.getProperty("user.dir")
                        //File separator ("/" on UNIX)
                        +System.getProperty("file.separator")
                        +"mv"
                        +System.getProperty("file.separator")
                );

        //前端用户头像地址
        registry.addResourceHandler("/avatorImages/**")
                .addResourceLocations("file:"
                +System.getProperty("user.dir")
                +System.getProperty("file.separator")
                +"avatorImages"
                +System.getProperty("file.separator")
                );

        //用户头像默认地址
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"
                        +System.getProperty("user.dir")
                        +System.getProperty("file.separator")
                        +"img"
                        +System.getProperty("file.separator")
                );

    }


}
