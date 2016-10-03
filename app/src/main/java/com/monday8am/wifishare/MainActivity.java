package com.monday8am.wifishare;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.monday8am.wifishare.flow.BasicDispatcher;
import com.monday8am.wifishare.flow.BasicKeyParceler;

import flow.Flow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        newBase = Flow.configure(newBase, this)
                .dispatcher(new BasicDispatcher(this))
                .defaultKey(new WifiListScreen)
                .keyParceler(new BasicKeyParceler())
                .install();
        super.attachBaseContext(newBase);
    }
}
