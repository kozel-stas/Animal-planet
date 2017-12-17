package com.company;

import java.util.Random;

public class Leopard extends Predator {
    public Leopard(){
        Random b=new Random();
        StepHealth=4;
        Name="Leopard";
        Size=2;
        Sex= b.nextBoolean();
        Stepeat=10;
        Wanttoeat=25;
        Steprepo=6;
        How_much_cells_you_see=10;
    }
}
