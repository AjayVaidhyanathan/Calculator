package com.think.rethink.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.security.spec.ECField;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TextView txtInput, txtOutput;
    String stxtInput = "", stxtOutput = "", num1 = "", current_operator = "",optr = "",lastoptr = "";
    Double numOne = 0.0, pt = 0.0, Result = 0.0;
    NumberFormat format;
    Boolean decimal = false,opt = false, changevalue = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInput = findViewById(R.id.txtInput);
        txtOutput = findViewById(R.id.txtOutput);

        format = new DecimalFormat("#.####");

    }

    public void onClickNumber(View v){
        Button n = (Button) v;
        stxtInput += n.getText();
        num1 += n.getText();
        Log.i(num1,"value of num1");
        numOne = Double.parseDouble(num1);
        switch (current_operator){
            case "":
                pt = numOne;
                stxtOutput= format.format(pt);
                break;

            case "+":
                pt = Result + numOne;
                stxtOutput = format.format(pt);
                break;

            case "-":
                pt = Result-numOne;
                stxtOutput = format.format(pt);
                break;

            case "x":
                pt = Result * numOne;
                stxtOutput = format.format(pt);
                break;

            case "/":
                try {
                    pt = Result / numOne;
                    stxtOutput = format.format(pt);

                }catch (Exception e){
                    stxtOutput=e.getMessage();
                }
                break;
        }
        opt = false;
        update();
    }

    public void onClickOperator(View v){
        Button op = (Button) v;
        if (stxtOutput != "") {
            if (!opt) {
                if (current_operator == "") {
                    stxtInput += op.getText();
                    num1 = "";
                    numOne = 0.0;
                    Result = pt;
                    stxtOutput = format.format(pt);
                    current_operator = op.getText().toString();
                    optr += op.getText().toString();
                    opt = true;
                    decimal = false;
                    update();
                } else {
                    stxtInput += op.getText();
                    num1 = "";
                    numOne = 0.0;
                    Result = pt;
                    stxtOutput = format.format(pt);
                    current_operator = op.getText().toString();
                    optr += op.getText().toString();
                    decimal = false;
                    opt = true;
                    update();
                }
                Log.i(optr,"all charc");
                update();
            }
        }
    }


    public void onDotClick(View v){
        if(!decimal){
            if (num1.length() == 0) {
                stxtInput += "0.";
                num1 += "0.";
                decimal = true;
                update();
            }else {
                stxtInput += ".";
                num1 += ".";
                decimal = true;
                update();
            }
        }
    }


    public void onClickClear(View v) {
        cleardata();
    }

    public void cleardata() {
        stxtInput = "";
        stxtOutput = "";
        current_operator = "";
        lastoptr="";
        optr = "";
        num1 = "";
        Result = 0.0;
        numOne = 0.0;
        pt = 0.0;
        opt = false;
        decimal = false;
        update();
    }


    public void onClickEqual(View v) {
        stxtInput = "";
        stxtOutput = format.format(pt);
        current_operator = "";
        optr = "";
        num1 = "";
        numOne = 0.0;
        pt = 0.0;
        Result = 0.0;
        update();

    }

    private char getcharfromLast(String s, int i) {
        char c = s.charAt(s.length() - i);
        return c;
    }

    public void removeuntilchar(String str, char chr) {
        char c = getcharfromLast(str, 1);
        if (c != chr) {
            str = removechar(str, 1);
            stxtInput = str;
            update();
            removeuntilchar(str, chr);
        }
    }

    public String removechar(String str, int i) {
        char c = str.charAt(str.length() - i);
        //we need to check if dot is removed or not
        if (c == '.' && !decimal) {
            decimal = false;
        }

        if (c == ' ') {
            return str.substring(0, str.length() - (i - 1));
        }
        return str.substring(0, str.length() - i);
    }

    public String valuebeforecharc(String str, String a){
        // Returns a substring containing all characters after a string.
        int posA = str.lastIndexOf(a);
        if (posA == -1) {
            return "";
        }
        int adjustedPosA = posA + a.length();
        if (adjustedPosA >= str.length()) {
            return "";
        }
        return str.substring(adjustedPosA);
    }

    public Double ondeletevalue(String stxtInput){
        if(!changevalue){
            switch (lastoptr){
                case "+":
                    Log.i(stxtInput, "Value of txtinput");
                    num1 = valuebeforecharc(stxtInput,lastoptr);Log.i(num1, "value of num1");
                    numOne = Double.parseDouble(num1);
                    Result = Result - numOne; Log.i(format.format(Result),"Line 208 - Value of result");
                    changevalue = true;

                    break;
                case "-":
                    Log.i(stxtInput, "Value of txtinput");
                    num1 = valuebeforecharc(stxtInput,lastoptr);Log.i(num1, "value of num1");
                    numOne = Double.parseDouble(num1);
                    changevalue = true;
                    Result = Result + numOne; Log.i(format.format(Result),"Line 217 - Value of result");
                    break;
                case "x":
                    try {
                        Log.i(stxtInput, "Value of txtinput");
                        num1 = valuebeforecharc(stxtInput, lastoptr);
                        Log.i(num1, "value of num1");
                        numOne = Double.parseDouble(num1);
                        changevalue = true;
                        Result = Result / numOne;
                        Log.i(format.format(Result), "Line 224 - Value of result");
                    }catch (Exception e){
                        stxtOutput = e.getMessage();
                    }break;
                case "/":
                    Log.i(stxtInput, "Value of txtinput");
                    num1 = valuebeforecharc(stxtInput,lastoptr);Log.i(num1, "value of num1");
                    numOne = Double.parseDouble(num1);
                    changevalue = true;
                    Result = Result * numOne; Log.i(format.format(Result),"Line 230 - Value of result");
                    break;
            }
        }return Result;
    }

    public void onClickDelete(View view) {
        if(stxtInput != "" && stxtOutput !=""){ Log.i("INSIDE","Line 233 - Working");
            if(stxtInput.length()<2){ Log.i("cleardata","Line 234 inside stxt.length()<2 loop");
               cleardata();
            }else{
                if (getcharfromLast(stxtInput, 1) == '.') { Log.i("Inside Dot Function", "Line - 237 Dot fun");
                    if (getcharfromLast(stxtInput, 2) == '0') {
                        Log.i("InsideDotFunlastcha = 0", "Line - 238 Dot fun");
                        num1 = "";
                        Log.i(num1, "Line 239Valueofnum1REMOVEdec");
                        numOne = 0.0;
                        pt = Result;
                        stxtInput = removechar(stxtInput, 2);
                        Log.i(stxtInput, "Line 241ValueofinputREMOVEdec");
                        stxtOutput = format.format(pt);
                        Log.i(format.format(pt), "Line 242ValueofinputREMOVEdec");
                        decimal = false;
                        update();
                    } else {
                        num1 = removechar(num1, 1);
                        Log.i("InsideDotFunlastcha = .", "Line - 246 Dot fun");
                        numOne = Double.parseDouble(num1);
                        stxtInput = removechar(stxtInput, 1);
                        stxtOutput = format.format(pt);
                        Log.i(stxtOutput, "Line -248 Value of output");
                        decimal = false;
                        update();
                    }
                }else{
                    if (getcharfromLast(stxtInput, 1) == '+' || getcharfromLast(stxtInput, 1) == '-' || getcharfromLast(stxtInput, 1) == 'x' || getcharfromLast(stxtInput, 1) == '/') {
                        if(optr.length()==1) {
                            Log.i("Inside optr loop", "Line - 255 optr loop");
                            stxtInput = removechar(stxtInput, 1);
                            optr = removechar(optr, 1);
                            Log.i(optr, "Line 267 - value of optr");
                            pt = Result;
                            Log.i(num1, "Line 256 - Value of num1");
                            Log.i(format.format(pt), "Line 256 - Value of Result");
                            num1 = stxtOutput;
                            current_operator = "";
                            update();
                        }else{
                            changevalue = false;
                            stxtInput = removechar(stxtInput,1);
                            pt = Result; Log.i(format.format(pt),"Line - 279 value of pt");
                            stxtOutput = format.format(pt);
                            Log.i(num1,"Line 280 - value of num1");
                            optr = removechar(optr,1);
                            lastoptr += getcharfromLast(optr,1); Log.i(lastoptr, "Line 282 - lastoptr");
                            Result = ondeletevalue(stxtInput); Log.i(format.format(Result),"Line - 282 value of result");
                            current_operator = lastoptr;
                            lastoptr = "";
                            update();
                        }
                    }else {
                        switch (current_operator) {
                            case "":
                                num1 = removechar(num1,1);
                                numOne = Double.parseDouble(num1);
                                Result = 0.0;
                                pt = numOne;
                                stxtInput = num1;
                                stxtOutput = format.format(pt);
                                update();
                                break;
                            case "+":
                                stxtInput = removechar(stxtInput,1);
                                num1 = removechar(num1,1);
                                if (num1.length() < 1) {
                                    numOne = 0.0;
                                }else{
                                    numOne = Double.parseDouble(num1);
                                }
                                pt = Result + numOne;
                                stxtOutput = format.format(pt);
                                Log.i(format.format(numOne),"line 310 - value of num1");
                                Log.i(format.format(pt),"line 311 - value of pt");
                                Log.i(format.format(Result),"line 312 - value of Result");
                                update();
                                break;
                            case "-":
                                stxtInput = removechar(stxtInput,1);
                                num1 = removechar(num1,1);
                                if (num1.length() < 1) {
                                    numOne = 0.0;
                                }else{
                                    numOne = Double.parseDouble(num1);
                                }
                                pt = Result - numOne;
                                stxtOutput = format.format(pt);
                                Log.i(format.format(numOne),"line 325 - value of num1");
                                Log.i(format.format(pt),"line 326 - value of pt");
                                Log.i(format.format(Result),"line 327 - value of Result");
                                update();
                                break;
                            case "x":
                                stxtInput = removechar(stxtInput,1);
                                num1 = removechar(num1,1);
                                if (num1.length() < 1) {
                                    numOne = 0.0;
                                    stxtOutput = format.format(Result);
                                }else{
                                    numOne = Double.parseDouble(num1);
                                    pt = Result * numOne;
                                    stxtOutput = format.format(pt);
                                }
                                Log.i(format.format(numOne),"line 340 - value of num1");
                                Log.i(format.format(pt),"line 341 - value of pt");
                                Log.i(format.format(Result),"line 342 - value of Result");
                                update();
                                break;
                            case "/":
                                stxtInput = removechar(stxtInput,1);
                                num1 = removechar(num1,1);
                                if (num1.length() < 1) {
                                    numOne = 0.0;
                                    stxtOutput = format.format(Result);
                                }else{
                                    numOne = Double.parseDouble(num1);
                                    pt = Result / numOne;
                                    stxtOutput = format.format(pt);
                                }
                                Log.i(format.format(numOne),"line 355 - value of num1");
                                Log.i(format.format(pt),"line 356 - value of pt");
                                Log.i(format.format(Result),"line 357 - value of Result");
                                update();
                                break;
                        }
                    }
                }
            }


        }else {
            Log.i("Outside", "Working");
        }
    }

        public void update() {
            txtInput.setText(stxtInput);
            txtOutput.setText(stxtOutput);

        }
    }