package com.jinxiaolu.ipc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Author:  Gavin
 * Email:   gavinking@163.com
 * Date:    2016/5/27
 * Desc:
 */
public class ByIntentActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_intent);
        String intentMsg = getIntent().getStringExtra("msg");
        Bundle bundle = getIntent().getBundleExtra("bundle");
        String bundleMsg = bundle.getString("msg");
        ((TextView)findViewById(R.id.tv_intent_msg)).setText(intentMsg);
        ((TextView)findViewById(R.id.tv_bundle_msg)).setText(bundleMsg);
    }
}
