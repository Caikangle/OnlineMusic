package com.hust.music;

import com.hust.music.dao.ListSongMapper;
import com.hust.music.dao.SingerMapper;
import com.hust.music.domain.ListSong;
import com.hust.music.domain.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class MusicApplicationTests {

    @Autowired
    SingerMapper singerMapper;

    @Autowired
    ListSongMapper listSongMapper;
    @Test
    void testSelectByPrimaryKey() {
        System.out.println(singerMapper.selectByPrimaryKey(46));
    }

    @Test
    void testInsert() {
        Singer singer = new Singer(46,"Tommy Shelby", (byte) 1,"/img/mm.jpg",new Date(),"湖北武汉","Peaky Blinders");
        System.out.println(singerMapper.insert(singer));
    }

    @Test
    void testUpdate() {
        Singer singer = new Singer(46,"Tommy Shelby", (byte) 1,"/img/fucking.jpg",new Date(),"England","Peaky Blinders");
        System.out.println(singerMapper.update(singer));
    }

    @Test
    void testSelectAll() {
        List<Singer> singers = singerMapper.selectAllSingers();
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }

    @Test
    void testSelectByName() {
        List<Singer> singers = singerMapper.selectByName("%e%");
        for (Singer singer : singers) {
            System.out.println(singer);
        }
    }

    @Test
    void testListSong(){
        List<ListSong> listSongs = listSongMapper.listSongOfSongListId(2);
        for (ListSong listSong : listSongs) {
            System.out.println(listSong.getSongId());
        }
    }
}
