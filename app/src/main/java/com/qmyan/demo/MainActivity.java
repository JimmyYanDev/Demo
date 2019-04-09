package com.qmyan.demo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /**
     * 提示文本框
     */
    private TextView messageText;
    /**
     * 测试按钮
     */
    private Button checkButton;
    /**
     * 三天时间的毫秒数
     */
    public static long THREE_DAYS_TIME_MILLIS = 1000 * 60 * 60 * 24 * 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText = findViewById(R.id.tv_message);
        checkButton = findViewById(R.id.btn_check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageText.setText(getMsg());
            }
        });
    }

    /**
     * 根据不同的打开时间，返回对应的提示信息
     * @return
     */
    private String getMsg() {
        String msg = null;
        // 当前时间
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        long lastOpenTime = sharedPreferences.getLong(Constants.SHARED_PREFERENCES_KEY_LAST_OPEN_TIME, 0);
        // 时间差
        long diff = currentTimeMillis - lastOpenTime - THREE_DAYS_TIME_MILLIS;
        if (Constants.IS_FIRST) {
            msg = "欢迎初次使用";
        } else if (diff > 0) {
            msg = "好久不见，欢迎再次使用";
        } else {
            msg = "欢迎经常使用";
        }
        return msg;
    }

    @Override
    public void onBackPressed() {
        // 加这一块为了效果更明显
        System.exit(0);
        super.onBackPressed();
    }
}
