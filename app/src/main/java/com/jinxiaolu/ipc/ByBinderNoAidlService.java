package com.jinxiaolu.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/28
 * Desc:
 */
public class ByBinderNoAidlService extends Service {
    private static final String DESCRIPTOR = "BinderNoAidlService";
    private Binder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class MyBinder extends Binder {
        @Override
        protected boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            switch (code) {
                case 0x110: {
                    data.enforceInterface(DESCRIPTOR);
                    int _arg0 = data.readInt();
                    int _arg1 = data.readInt();
                    int _result = _arg0 * _arg1;
                    reply.writeNoException();
                    reply.writeInt(_result);
                    return true;
                }

                case 0x111: {
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    Log.d(DESCRIPTOR, _arg0);
                    reply.writeNoException();
                    return true;
                }
            }
            return super.onTransact(code, data, reply, flags);
        }
    }
}
