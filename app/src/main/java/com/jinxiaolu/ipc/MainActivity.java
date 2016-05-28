package com.jinxiaolu.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.jinxiaolu.ipc.bean.Msg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ipc by intent
        findViewById(R.id.btn_intent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ByIntentActivity.class);
//                intent.setAction("com.jinxiaolu.ipc.remote1");
                Bundle bundle = new Bundle();
                bundle.putString("msg", "Msg in bundle from main process");
                intent.putExtra("bundle", bundle);
                intent.putExtra("msg", "Msg in intent from main process");
                startActivity(intent);
            }
        });

        //ipc by file
        findViewById(R.id.btn_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeMsg2File();
                Intent intent = new Intent(MainActivity.this, ByFileActivity.class);
                startActivity(intent);

            }
        });

        //ipc by messenger
        findViewById(R.id.btn_messenger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ByMessengerActivity.class);
                startActivity(intent);
            }
        });

        //ipc by socket
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BySocketActivity.class);
                startActivity(intent);
            }
        });

        //ipc by binder with AIDl
        findViewById(R.id.btn_binder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ByBinderActivity.class);
                startActivity(intent);
            }
        });

        //ipc by binder without AIDL
        findViewById(R.id.btn_binder_no_aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ByBinderNoAidlActivity.class);
                startActivity(intent);
            }
        });
    }

    private void writeMsg2File() {
        File file = new File(Environment.getExternalStorageDirectory(), "myMsg");
        Log.d("IPC_DEMO", file.getAbsolutePath());
        Msg msg = new Msg();
        msg.title = "I am title from main process";
        msg.content = "i am content from main process";

        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(msg);
            oos.writeObject(null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != oos) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
