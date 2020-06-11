package com.example.labrynth.Models;

import java.util.ArrayList;
import java.util.Set;

public class Piece {
    private String value;
    private ArrayList<Integer> directions;
    private String imgVal;
    int beginrotate;
    public boolean visited = false;

    public int getBeginrotate() {
        return beginrotate;
    }

    public void setBeginrotate(int beginrotate) {
        this.beginrotate = beginrotate;
    }

    public Piece(String value, ArrayList<Integer> directions, String imgVal){
        this.value = value;
        this.directions = directions;
        this.imgVal = imgVal;
    }
    public Piece(String value, ArrayList<Integer> directions, String imgVal, int beginRotate){
        this.value = value;
        this.directions = directions;
        this.imgVal = imgVal;
        this.beginrotate = beginRotate;
    }

    public void addRotate(){
        beginrotate = (beginrotate + 1) % 4;
    }
    public void minusRotate(){
        beginrotate = mod((beginrotate -1), 4);
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ArrayList<Integer> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<Integer> directions) {
        this.directions = directions;
    }

    public String getImgVal() {
        return imgVal;
    }

    public void setImgVal(String imgVal) {
        this.imgVal = imgVal;
    }

    public void rotateVals(){
        for(int i = 0; i < directions.size(); ++i){
            directions.set(i, (directions.get(i) + beginrotate) % 4);
        }
    }
    public int mod(int x, int y)
    {
        int result = x % y;
        return result < 0? result + y : result;
    }
}
