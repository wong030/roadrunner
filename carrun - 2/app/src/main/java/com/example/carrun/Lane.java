package com.example.carrun;

public class Lane {
    public float xCoord;

    public Lane(int l){
        if(l == -1){
            xCoord = -4.f;
        }else if(l == 0){
            xCoord = 0.0f;
        }else{
            xCoord = 4.f;
        }
    }
}
