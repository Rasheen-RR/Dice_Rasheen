package com.infinium.dice_rasheen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private RadioGroup diceGroupTwo;
    private RadioGroup diceGroupOne;

    private int numberOfSides = 4;
    private int lowestSide = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button oneRoll = (Button) findViewById(R.id.one_roll);
        Button twoRolls = (Button) findViewById(R.id.two_roll);
        Button clear = (Button) findViewById(R.id.clear);
        final TextView resultView = (TextView) findViewById(R.id.result_view);

        diceGroupOne = (RadioGroup) findViewById(R.id.dice_group_1);
        diceGroupTwo = (RadioGroup) findViewById(R.id.dice_group_2);
        diceGroupOne.setOnCheckedChangeListener(listener1);
        diceGroupTwo.setOnCheckedChangeListener(listener2);


        oneRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int diceRoll = rollDice();
                resultView.setText(String.format("Dice Rolled : %s", diceRoll));
            }
        });

        twoRolls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int diceRoll = rollDice();
                int diceRoll2 = rollDice();
                resultView.setText(String.format("Dice 1 Rolled : %s , Dice 2 Rolled : %s ", diceRoll, diceRoll2));
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultView.setText("-");
            }
        });
    }


    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                diceGroupTwo.setOnCheckedChangeListener(null); // remove the listener before clearing so we don't throw that stackoverflow exception(like Vladimir Volodin pointed out)
                diceGroupTwo.clearCheck(); // clear the second RadioGroup!
                diceGroupTwo.setOnCheckedChangeListener(listener2); //reset the listener
                Log.e("XXX2", "do the work");
                switch (checkedId){
                    case R.id.four_sided:
                        numberOfSides = 4;
                        lowestSide = 1;
                        break;
                    case R.id.eight_sided:
                        numberOfSides = 8;
                        lowestSide = 1;
                        break;
                    case R.id.twelve_sided:
                        numberOfSides = 12;
                        lowestSide = 1;
                        break;
                    case R.id.thirty_sided:
                        numberOfSides = 30;
                        lowestSide = 1;
                        break;
                }
            }
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId != -1) {
                diceGroupOne.setOnCheckedChangeListener(null);
                diceGroupOne.clearCheck();
                diceGroupOne.setOnCheckedChangeListener(listener1);
                Log.e("XXX2", "do the work");
                switch (checkedId){
                    case R.id.six_sided:
                        numberOfSides = 6;
                        lowestSide = 1;
                        break;
                    case R.id.ten_sided:
                        numberOfSides = 10;
                        lowestSide = 1;
                        break;
                    case R.id.twenty_sided:
                        numberOfSides = 20;
                        lowestSide = 1;
                        break;
                    case R.id.true_ten_sided:
                        numberOfSides = 10;
                        lowestSide = 0;
                        break;
                }
            }
        }
    };

    public int rollDice(){
        int randomNum =  ThreadLocalRandom.current().nextInt(lowestSide, numberOfSides + 1);
        return randomNum;
    }



}