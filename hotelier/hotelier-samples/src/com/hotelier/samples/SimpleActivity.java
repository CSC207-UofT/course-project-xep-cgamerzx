package com.hotelier.samples;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by dd on 14-7-20.
 */
public class SimpleActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple);
    }
}