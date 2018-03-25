package com.example.chenjiayuan.doingexercise;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chenjiayuan on 3/24/18.
 */

public class Session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

}
