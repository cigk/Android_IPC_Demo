package com.jinxiaolu.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:
 */
public class SocketService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerSocket serverSocket = new ServerSocket(9999);
                    while (true) {
                        final Socket client = serverSocket.accept();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                processClient(client);
                            }
                        }).start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    private void processClient(Socket client) {
        try {
            //true for auto flush
            PrintWriter pw = new PrintWriter(new BufferedWriter(new
                    OutputStreamWriter(client.getOutputStream())), true);
            pw.println("Msg get");
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg;
            while ((msg = br.readLine()) != null) {
                Log.d("BY_SOCKET", msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
