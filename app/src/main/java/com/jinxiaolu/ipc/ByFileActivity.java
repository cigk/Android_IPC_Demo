package com.jinxiaolu.ipc;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.jinxiaolu.ipc.bean.Msg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:    need "android.permission.WRITE_EXTERNAL_STORAGE"
 */
public class ByFileActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_file);
        Msg msg = readFromFile();
        ((TextView) findViewById(R.id.tv_file_msg)).setText(msg.title + msg.content);
    }

    private Msg readFromFile() {
        File file = new File(Environment.getExternalStorageDirectory(), "myMsg");
        if (!file.exists()) return null;
        ObjectInputStream ois = null;
        Msg msg = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            msg = (Msg) ois.readObject();
            return msg;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return msg;
    }
}
