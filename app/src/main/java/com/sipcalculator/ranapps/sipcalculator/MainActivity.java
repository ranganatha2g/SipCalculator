package com.sipcalculator.ranapps.sipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    RadioGroup investTypeGroup;
    EditText investAmount, investDuration, investInterest;
    Button investButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
    }

    private void setUpViews()
    {
        investTypeGroup = findViewById(R.id.investType);
        investAmount = findViewById(R.id.invest_amount);
        investDuration = findViewById(R.id.invest_duration);
        investInterest = findViewById(R.id.invest_interest);
        investButton = findViewById(R.id.invest_button);

        investButton.setOnClickListener(investButtonClick);
    }


    private View.OnClickListener investButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(validateViews())
            {
                calculatePremiumAmount();
            }
        }
    };


    private void calculatePremiumAmount()
    {
        if(investTypeGroup.getCheckedRadioButtonId() == R.id.lumpSumInvest)
        {
            calculateLumpsum();
        }
        else
        {
            Log.d("TYPE", "SIP");
        }
    }

    private void calculateLumpsum()
    {
        int principleAmount = Integer.valueOf(investAmount.getText().toString());

        int duration = Integer.valueOf(investDuration.getText().toString());

        int rateOfInterest = Integer.valueOf(investInterest.getText().toString());

        double temp = Math.pow( 1+(rateOfInterest)*1.0/100, duration);

        double ans = principleAmount *  temp;

        Log.d("AMOUNT", String.valueOf(ans));
    }

    private boolean validateViews()
    {
        if(investTypeGroup.getCheckedRadioButtonId() == -1)
        {
            return false;
        }
        if( investAmount.getText() == null || investAmount.getText().toString().equals(""))
        {
            return false;
        }
        if( investDuration.getText() == null || investDuration.getText().toString().equals(""))
        {
            return false;
        }


        return true;
    }
}
