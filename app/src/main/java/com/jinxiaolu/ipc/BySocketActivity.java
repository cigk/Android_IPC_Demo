package com.jinxiaolu.ipc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:    need "android.permission.INTERNET"
 */
public class BySocketActivity extends AppCompatActivity {

    private ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_socket);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("com.jinxiaolu.socket_service");
                startService(intent);
            }
        });

        findViewById(R.id.btn_send_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service.execute(new MyRunnable());
            }
        });

    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Socket socket = new Socket("localhost", 9999);
                PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                        .getOutputStream())), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                pw.println("Socket connection from ui process1");
                pw.println("Socket connection from ui process2");
                pw.println("Socket connection from ui process3");
                pw.println("Socket connection from ui process4");
                pw.println("Socket connection from ui process5");
                String str = br.readLine();
                Log.d("BY_SOCKET", str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}