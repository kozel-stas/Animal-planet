package com.company;

import java.util.Random;

public class Predator extends Animal {
    @Override
    public boolean Eat(Organism Eatit) {
        if (Eatit.x==x && Eatit.y==y && Eatit!=null && Eatit.getClass().getSuperclass()==Herbivore.class){
            int a=(100-Wanttoeat)*Size;
            int b=Eatit.Health*Eatit.Size;
            Random Rand=new Random();
            int c= Rand.nextInt(4);
            if (c>0) {
                if (b - a > 0) {
                    Wanttoeat = 100;
                    Eatit.Health=0;
                    return true;
                } else {
                    Wanttoeat += (int) (Eatit.Health * Eatit.Size / Size);
                    Eatit.Health=0;
                    return true;
                }
            }
            else{
                int z=((Animal) Eatit).How_much_cells_you_see;
                ((Animal) Eatit).How_much_cells_you_see=15;
                ((Animal) Eatit).RandGo();
                Animal.Stroke((Animal) Eatit,((Animal) Eatit).getWantX(),((Animal) Eatit).getWantY());
                ((Animal) Eatit).setWantX(-1);
                ((Animal) Eatit).setWantY(-1);
                ((Animal) Eatit).How_much_cells_you_see=z;
                return false;
            }
        }
        return false;
    }//покушать, шанс 1 к 5, что добыча убежит

    @Override
    public Organism FindEat() {
        int[] SetAns=BorderStep();
        int Leftx=SetAns[0], Lefty=SetAns[1];
        int Rightx=SetAns[2], Righty=SetAns[3];
        for (int i = Leftx; i < Rightx; i++)
            for (int j = Lefty; j < Righty; j++) {
                Organism a=Field.Board[i][j].FindOrganism(Herbivore.class);
                if (Field.Board[i][j] != null &&  a!= null) {
                    if (Size+1>=a.Size)
                     return a;
                }
            }
        return null;
    } //поиск
}
