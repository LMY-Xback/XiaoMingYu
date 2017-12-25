package com.example.limingyu20171123;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by Administrator on 2017/11/23 0023.
 */

public class MApp extends Application{
    File file=new File(Environment.getExternalStorageDirectory()+"/"+"month");
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480,800)
                .memoryCache(new LruMemoryCache(2*1024*1024))
                .memoryCacheSize(50*1024*1024)
                .diskCache(new UnlimitedDiskCache(file))//缓存
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
