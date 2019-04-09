package com.qmyan.demo;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by MichealYan on 2019/4/9.
 * Email: 956462326@qq.com
 * Describe:
 **/
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        recordLunchTime();
    }

    /**
     * 记录应用的启动时间
     */
    private void recordLunchTime() {
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // 判断是否是第一次启动
        Constants.IS_FIRST = sharedPreferences.getBoolean(Constants.SHARED_PREFERENCES_KEY_IS_FIRST, true);
        if (Constants.IS_FIRST) {
            editor.putBoolean(Constants.SHARED_PREFERENCES_KEY_IS_FIRST, false);
        }
        // 将每次启动的时间保存在SharedPreferences中
        editor.putLong(Constants.SHARED_PREFERENCES_KEY_LAST_OPEN_TIME, currentTimeMillis);
        editor.commit();
    }
}
