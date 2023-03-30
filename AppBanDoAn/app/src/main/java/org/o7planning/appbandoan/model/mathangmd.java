package org.o7planning.appbandoan.model;

import java.util.ArrayList;

public class mathangmd {
    boolean success;
    String message;
    ArrayList<mathang> result;




    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<mathang> getResult() {
        return result;
    }

    public void setResult(ArrayList<mathang> result) {
        this.result = result;
    }
}
