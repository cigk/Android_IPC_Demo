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
import android.widget.Toast;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/28
 * Desc:
 */
public class ByBinderNoAidlActivity extends AppCompatActivity {

    private IBinder binder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = service;
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
                intent.setAction("com.jinxiaolu.binder_service_no_aidl");
                intent.setPackage(getPackageName());// for Service Intent  must be explitict error
                bindService(intent, conn, Context.BIND_AUTO_CREATE);
            }
        });


        findViewById(R.id.btn_transact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execMultiply();
                sendMsg2Service();
            }
        });
    }

    /**
     * 执行两数相乘
     */
    private void execMultiply() {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        int _result;
        try {
            _data.writeInterfaceToken("BinderNoAidlService");
            _data.writeInt(50);
            _data.writeInt(12);
            binder.transact(0x110, _data, _reply, 0);//0为双向通信，有返回值
            _reply.readException();
            _result = _reply.readInt();
            Toast.makeText(this, _result + "", Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }

    /**
     * 发送一句话个服务端
     */
    private void sendMsg2Service() {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
            _data.writeInterfaceToken("BinderNoAidlService");
            _data.writeString("Hahaahahah");
            binder.transact(0x111, _data, _reply, 1);//1为单向，无返回值
            _reply.readException();

        } catch (RemoteException e) {
            e.printStackTrace();
        } finally {
            _reply.recycle();
            _data.recycle();
        }
    }
}
