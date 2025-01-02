package com.example.calculator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class Calculator extends AppCompatActivity {
    Button btn;
    TextView display;
    String lhs = "", rhs = "", oldSign = "" , newSign = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calculator);
        display = findViewById(R.id.tv_title);
    }
    public void onNumberClick(View view){
        btn = (Button) view;
        if(display.getText().toString().contains(".") && btn.getText().equals(".")){
            return;
        } else {
            display.append(btn.getText());
            if(oldSign == "") {
                lhs = display.getText().toString();
            } else if (oldSign !="" && newSign == ""){
                rhs = display.getText().toString();
            } else if(oldSign !="" && newSign != ""){
                lhs = String.valueOf(calculate(lhs, oldSign, rhs));
                oldSign = newSign;
                newSign = "";
                rhs = display.getText().toString();
            }
            }
        }

    public void onOperatorClick(View view) {
        btn = (Button) view;
        display.append(btn.getText());
        if(Objects.equals(oldSign, "")) {
            oldSign = btn.getText().toString();
            display.setText("");
            Log.e("altSign", oldSign);
            return;
        }
        if(!Objects.equals(oldSign, "")){
            assert btn != null;
            newSign = btn.getText().toString();
            display.setText("");
            Log.e("newSign", newSign);
        }

    }

    double calculate( String lhs, String operator, String rhs) {
        double result = 0;
        double leftSide = Double.parseDouble(lhs);
        Log.e("leftSide", leftSide + "");
        Log.e("righttSide", rhs + "");
        double rightSide = Double.parseDouble(rhs);
        switch(oldSign){
            case "+": {
                result = leftSide + rightSide;
                Log.e("result", result + "");
                break;
            }
            case "-": {
                result = leftSide - rightSide;
                break;
            }
            case "*": {
                result = leftSide * rightSide;
                break;
            }
            case "/": {
                result = leftSide / rightSide;
                break;
            }
            default:
                result = 0;
                break;
        }
        return result;
    }

    public void onEqualClick(View view){
        display.setText(String.valueOf(calculate(lhs, oldSign, rhs)));
        lhs = "";
        rhs = "";
        oldSign = "";
        newSign = "";
    }

}
