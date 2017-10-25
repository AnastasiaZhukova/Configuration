package com.github.anastasiazhukova.configuration;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.anastasiazhukova.configuration.calculator.CalculatorActivity;
import com.github.anastasiazhukova.configuration.configuration.Configuration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkCodeVersion();
    }

    private void startCalculatorActivity() {
        startActivity(new Intent(this, CalculatorActivity.class));
    }

    private void checkCodeVersion() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final int finalVersion;
                int version = 0;
                try {
                    version = Configuration.getVersion();
                } catch (Exception pE) {
                    //ignored
                }
                finalVersion = version;

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        compareToBuildVersion(finalVersion);
                    }
                });
            }
        }).
                start();

    }

    private void compareToBuildVersion(final int pVersion) {

        if (pVersion > BuildConfig.VERSION_CODE) {
            showAlertDialog();
        } else {
            startCalculatorActivity();
        }

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Update is available");
        alertDialog.setMessage("New update is available");
        alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Go to store to update", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setNegativeButton("Later", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Please do it later", Toast.LENGTH_SHORT).show();
                startCalculatorActivity();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

}
