package com.example.bindservicetest;

import com.example.bindservicetest.CalService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private EditText mEditA;
    private EditText mEditB;
    private Button mBtn;
    private TextView mResult;
    
    private CalService mBindService;
    private MyBinder mBinder;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // bind service
        Intent intent = new Intent(MainActivity.this, CalService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        
        mEditA = (EditText) findViewById(R.id.editText1);
        mEditB = (EditText) findViewById(R.id.editText2);
        mResult = (TextView) findViewById(R.id.textView2);
        
        mBtn = (Button) findViewById(R.id.button1);
        mBtn.setOnClickListener(new OnClickListener() {            
            @Override
            public void onClick(View arg0) {
                int valueA = Integer.valueOf(mEditA.getText().toString());
                int valueB = Integer.valueOf(mEditB.getText().toString());
                //用Service來做加法
                int result = mBindService.add(valueA, valueB);
                
                mResult.setText("=" + result);
            }
        });
    }

    @Override
    protected void onDestroy() {
        this.unbindService(conn);
        super.onDestroy();
    }
    
    private ServiceConnection conn = new ServiceConnection() {        
        @Override
        public void onServiceDisconnected(ComponentName name) {               
        }
           
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinder = (MyBinder)service;
            mBindService = mBinder.getService();
        }

    };
}
