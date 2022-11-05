package com.example.basiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultview,solutionview;
    MaterialButton buttonC,buttonOpenBrack,buttonCloseBrack;
    MaterialButton buttonDivide,buttonMultiple,buttonAdd,buttonSubtract,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAc,buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultview=findViewById(R.id.result_view);
        solutionview=findViewById(R.id.solution_view);

        assignId(buttonC,R.id.button_c);
        assignId(buttonOpenBrack,R.id.button_openbracket);
        assignId(buttonCloseBrack,R.id.button_closebracket);
        assignId(buttonDivide,R.id.button_divide);
        assignId(buttonMultiple,R.id.button_multiply);
        assignId(buttonAdd,R.id.button_add);
        assignId(buttonSubtract,R.id.button_subtract);
        assignId(buttonEquals,R.id.button_equal);
        assignId(button0,R.id.button_0);
        assignId(button1,R.id.button_1);
        assignId(button2,R.id.button_2);
        assignId(button3,R.id.button_3);
        assignId(button4,R.id.button_4);
        assignId(button5,R.id.button_5);
        assignId(button6,R.id.button_6);
        assignId(button7,R.id.button_7);
        assignId(button8,R.id.button_8);
        assignId(button9,R.id.button_9);
        assignId(buttonAc,R.id.button_ac);
        assignId(buttonDot,R.id.button_dot);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCalculate=solutionview.getText().toString();

        if(buttonText.equals("AC")){
            solutionview.setText("");
            resultview.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionview.setText(resultview.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else {
            dataToCalculate=dataToCalculate+buttonText;
        }
        solutionview.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);

        if(!finalResult.equals("Error")){
            resultview.setText(finalResult);
        }
    }

    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        } catch (Exception e){
            return "Error";
        }
    }
}