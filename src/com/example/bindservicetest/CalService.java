package com.example.bindservicetest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalService extends Service{
    private MyBinder myBinder = new MyBinder();    

    public int add(int a, int b) {
        int c = a + b;
        return c;
    }
    
    @Override
    public IBinder onBind(Intent arg0) {
        return myBinder;
    }
    
    public class MyBinder extends Binder{
        // 回傳Service,使Activity可以利用service內的東西
        public CalService getService(){
            return CalService.this;
        }
    }      
    
}
