package com.xuzhu.HalfLifeCalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.xuzhu.HalfLifeCalculator.R.id.result;

public class MainActivity extends AppCompatActivity {

    public static final int SCALE = 10;
    private EditText originalWeight, time, lastWeight, halfLife;
    private double originalWeightD, timeD, lastWeightD, halfLifeD;
    public int count;
    public int type = -1;
    private TextView mResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        originalWeight = (EditText) findViewById(R.id.originalWeight);
        time = (EditText) findViewById(R.id.time);
        lastWeight = (EditText) findViewById(R.id.lastWeight);
        halfLife = (EditText) findViewById(R.id.halfLife);
        mResult = (TextView) findViewById(result);

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(originalWeight.getText().toString())) {
                    originalWeightD = Double.parseDouble(originalWeight.getText().toString());
                    count++;
                } else {
                    type = 0;
                }

                if (!TextUtils.isEmpty(time.getText().toString())) {
                    timeD = Double.parseDouble(time.getText().toString());
                    count++;
                } else {
                    type = 1;
                }


                if (!TextUtils.isEmpty(lastWeight.getText().toString())) {
                    lastWeightD = Double.parseDouble(lastWeight.getText().toString());
                    count++;
                } else {
                    type = 2;
                }

                if (!TextUtils.isEmpty(halfLife.getText().toString())) {
                    halfLifeD = Double.parseDouble(halfLife.getText().toString());
                    count++;
                } else {
                    type = 3;
                }


                double result = -1;

                if (count != 3) {
                    Toast.makeText(MainActivity.this, "输入错误", Toast.LENGTH_SHORT).show();
                } else {
                    if (type == 0) {
                        result = getOriginalWeight(halfLifeD, timeD, lastWeightD);
                    } else if (type == 1) {
                        result = getTime(halfLifeD, originalWeightD, lastWeightD);
                    } else if (type == 2) {
                        result = getLastWeight(halfLifeD, timeD, originalWeightD);
                    } else if (type == 3) {
                        result = getHalfLife(lastWeightD, timeD, originalWeightD);
                    }

                    mResult.setText(result + "");
                }

                count = 0;
            }
        });
    }

    //获取原始的重量
    public double getOriginalWeight(double halfLife, double time, double lastWeight) {

        double halfLifeTimes = ArithmeticUtil.div(time, halfLife, SCALE);

        double div = Math.pow(0.5, halfLifeTimes);
        double originalWeight = ArithmeticUtil.div(lastWeight, div, SCALE);
        return originalWeight;
//        halfLifeTimes = time/halfLife
//        originalWeight = lastWeight/0.5**halfLifeTimes
    }


    //获取原始的重量
    public double getTime(double halfLife, double originalWeight, double lastWeight) {

        double temp = ArithmeticUtil.div(lastWeight, originalWeight, SCALE);
        double halfLifeTimes = ArithmeticUtil.div(Math.log(temp), Math.log(0.5), SCALE);
        double time = ArithmeticUtil.mulWithScale(halfLifeTimes,halfLife,SCALE);
        return time;

//        halfLifeTimes = math.log(lastWeight / originalWeight,0.5)
//        time = halfLifeTimes*halfLife

    }


    //获取原始的重量
    public double getLastWeight(double halfLife, double time, double originalWeight) {

        double div = ArithmeticUtil.div(time, halfLife, SCALE);
        double temp2 = Math.pow(2, div);
        double lastWeight = ArithmeticUtil.div(originalWeight, temp2, SCALE);
        return lastWeight;


//        lastWeight = originalWeight / 2**(time/halfLife)
    }

    //获取原始的重量
    public double getHalfLife(double lastWeight, double time, double originalWeight) {
        double div = ArithmeticUtil.div(lastWeight, originalWeight, SCALE);
        double halfLifeTimes = ArithmeticUtil.div(Math.log(div), Math.log(0.5), SCALE);
        double halfLife = ArithmeticUtil.div(time,halfLifeTimes,SCALE);
        return halfLife;

//        halfLifeTimes = math.log(lastWeight / originalWeight,0.5)
//        halfLife = time / halfLifeTimes
    }


}
