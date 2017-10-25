package com.github.anastasiazhukova.configuration.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.github.anastasiazhukova.configuration.R;

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

}
