package com.shelectric.bruce.a6_playmusic;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by admin on 7/14/2017.
 */

public class MusicService extends Service {

    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MusicController();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();//彻底释放player播放器（C语言不能没有自动回收的功能，底层的数据库-->手动关闭、多媒体操作等都是C写的）
        player = null;//方便系统回收
    }

    class MusicController extends Binder implements MusicInterface {

        @Override
        public void play() {
            MusicService.this.play();
        }

        @Override
        public void pause() {
            MusicService.this.pause();

        }

        @Override
        public void continuePlay() {
            MusicService.this.continuePlay();

        }


    }

    public void play() {
        player.reset();
        try {
            //加载多媒体文件
/*            player.setDataSource("sdcard/feng.mp3");
            player.prepare();
            player.start();*/

            player.setDataSource("http://192.168.137.1/server/qilixiang.mp3");
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    player.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void continuePlay() {
        player.start();
    }

    public void pause() {
        player.pause();
    }

}
