package com.github.anastasiazhukova.configuration.calculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.github.anastasiazhukova.configuration.BuildConfig;
import com.github.anastasiazhukova.configuration.R;
import com.github.anastasiazhukova.configuration.configuration.Configuration;

public class CalculatorActivity extends AppCompatActivity {

    private static final String TAG = CalculatorActivity.class.getSimpleName();
    private EditText mInput;
    private View mCalculateButton;
    private ICalculator mCalculator;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mCalculator = new Calculator();
        initViews();
    }

    private void initViews() {
        mInput = (EditText) findViewById(R.id.editText_input);
        mCalculateButton = findViewById(R.id.button_calculate);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isCodeVersionChecked) {
                    checkCodeVersion();
                }
                calculate(mInput.getText().toString());

            }
        });
    }

    private final String errorMessage = "Error!";

    private void calculate(final String pInput) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                final String finalResult;
                String result;
                try {
                    result = mCalculator.evaluate(pInput);
                } catch (final Exception pE) {
                    result = errorMessage;
                    Log.e(TAG, pE.getMessage());
                }
                finalResult = result;
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        showResult(finalResult);
                    }
                });
            }
        }).

                start();

    }

    private void showResult(final String pResult) {
        mInput.setText(pResult);
    }

    boolean isCodeVersionChecked = false;

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
        }
        isCodeVersionChecked = true;

    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Update is available");
        alertDialog.setMessage("New update is available");
        alertDialog.setNegativeButton("Update", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(CalculatorActivity.this, "Go to " + BuildConfig.STORE_NAME + " to update", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

}
