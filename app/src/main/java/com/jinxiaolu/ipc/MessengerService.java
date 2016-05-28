package com.jinxiaolu.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:
 */
public class MessengerService extends Service {

    private Messenger messenger = new Messenger(new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                for (int i = 0; i < 10; i++) {
                    Log.d("BY_MESSENGER", "I am service process and I got msg from ui process");
                }
                Message message = Message.obtain();
                message.what = 100;
                try {
                    msg.replyTo.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            super.handleMessage(msg);
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }
}
