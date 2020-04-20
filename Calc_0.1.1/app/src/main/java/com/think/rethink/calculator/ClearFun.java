package com.think.rethink.calculator;

public class ClearFun {
    String stxtInput = "",stxtOutput = "",current_operator="",lastoptr="",optr="",num1="";
    Double numOne=0.0,Result=0.0,pt=0.0;
    Boolean opt = false,decimal = false;

    public String getStxtInput() {
        return stxtInput;
    }

    public void setStxtInput(String stxtInput) {
        this.stxtInput = stxtInput;
    }

    public String getStxtOutput() {
        return stxtOutput;
    }

    public void setStxtOutput(String stxtOutput) {
        this.stxtOutput = stxtOutput;
    }

    public String getCurrent_operator() {
        return current_operator;
    }

    public void setCurrent_operator(String current_operator) {
        this.current_operator = current_operator;
    }

    public String getLastoptr() {
        return lastoptr;
    }

    public void setLastoptr(String lastoptr) {
        this.lastoptr = lastoptr;
    }

    public String getOptr() {
        return optr;
    }

    public void setOptr(String optr) {
        this.optr = optr;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public Double getNumOne() {
        return numOne;
    }

    public void setNumOne(Double numOne) {
        this.numOne = numOne;
    }

    public Double getResult() {
        return Result;
    }

    public void setResult(Double result) {
        Result = result;
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Boolean getOpt() {
        return opt;
    }

    public void setOpt(Boolean opt) {
        this.opt = opt;
    }

    public Boolean getDecimal() {
        return decimal;
    }

    public void setDecimal(Boolean decimal) {
        this.decimal = decimal;
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
    }
}
