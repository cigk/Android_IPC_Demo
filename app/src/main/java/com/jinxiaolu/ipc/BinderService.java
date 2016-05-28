package com.jinxiaolu.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/28
 * Desc:
 */
public class BinderService extends Service {

    private IByBinderInterface.Stub byBinderInterface = new IByBinderInterface.Stub() {
        @Override
        public void add(int x, int y) throws RemoteException {
            int result = x + y;
            Log.d("BY_BINDER", result + "");
        }

        @Override
        public void sendMsg(String msg) throws RemoteException {
            Log.d("BY_BINDER", msg);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return byBinderInterface;
    }
}
