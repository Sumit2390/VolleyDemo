package com.example.toshiba.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

public class First extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.first_screen);

        setContentView(R.layout.activity_main);
    }
}
