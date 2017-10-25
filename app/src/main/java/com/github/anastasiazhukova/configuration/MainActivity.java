package com.github.anastasiazhukova.configuration;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.anastasiazhukova.configuration.calculator.CalculatorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startCalculatorActivity();
    }

    private void startCalculatorActivity() {
        startActivity(new Intent(this, CalculatorActivity.class));
    }
}
