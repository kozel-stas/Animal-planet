package com.company;

public class Flower extends Plants{
    public Flower(){
        StepHealth=9;
        Name="Flower";
        Steprepo=50;
        Size=2;
    }

    public Organism Clone(int [] ans){
        Flower b=new Flower();
        b.x=ans[0];
        b.y=ans[1];
        Field.Board[b.x][b.y].AddOrganism(b);
        return b;
    }
}
