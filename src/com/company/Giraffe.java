package com.company;

import java.util.Random;

public class Giraffe extends Herbivore {
    public Giraffe(){
        Random b=new Random();
        StepHealth=0;
        Name="Giraff";
        Size=2;
        Sex= b.nextBoolean();
        Stepeat=0;
        Steprepo=69;
        How_much_cells_you_see=10;
    }

    public Organism Clone(){
        Giraffe b=new Giraffe();
        b.x=x;
        b.y=y;
        Field.Board[x][y].AddOrganism(b);
        return b;
    }
}
