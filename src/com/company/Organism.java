package com.company;

public class Organism {//родитель живых
    protected int Wantreproduction=100;// ячейка репродукции
    protected int Steprepo;//шаг
    protected String Name;//имя
    protected int Size;
    protected int x,y;//координаты
    protected int Health=100;
    protected int StepHealth;//шаг здоровья

    public boolean Aging() {
        if (Health-StepHealth>0) {
            Health = Health - StepHealth;
            return true;
        }
        else {
            Health=0;
            return false;
        }
    }//старение

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Organism Clone(){
        return null;
    }

    public boolean Rep(){
        if (Wantreproduction-Steprepo>0) {
            Wantreproduction -= Steprepo;
            return true;
        }
        else {
            Wantreproduction=0;
            return false;
        }
    }//уменьшение репродукции

}
