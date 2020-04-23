package com.volkodav4ik.model;

public class Result {

    private String result;

    public Result() {
    }

    public Result(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result of transaction: " + result;
    }

}
