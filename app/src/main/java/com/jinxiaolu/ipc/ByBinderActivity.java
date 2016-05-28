package com.jinxiaolu.ipc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/28
 * Desc:
 */
public class ByBinderActivity extends AppCompatActivity {

    IByBinderInterface binder;

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IByBinderInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_binder);

        findViewById(R.id.btn_bind).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.jinxiaolu.binder_service");
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });


        findViewById(R.id.btn_transact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binder.add(1, 2);
                    binder.sendMsg("Hahahahahahah");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
