package com.example.projetmobile;

public class Conversation {
    private String mName;
    private String mMessage;


    public Conversation(String name,String message) {
        mName = name;
        mMessage = message;

    }

    public String getName() {
        return mName;
    }

    public String getMessage() {
        return mMessage;
    }



    public void setLastMessage(String s) {
        mMessage =s;
    }


}

