package com.jinxiaolu.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:
 */
public class ByMessengerActivity extends AppCompatActivity {

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                for (int i = 0; i < 10; i++) {
                    Log.d("BY_MESSENGER", "I am ui process and I got msg from service process");
                }
            }
            super.handleMessage(msg);
        }
    });

    private Messenger remoteMessenger;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteMessenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_messenger);

        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.jinxiaolu.messenger_service");
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });


        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message message = Message.obtain();
                message.what = 1;
                message.replyTo = messenger;
                try {
                    remoteMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
