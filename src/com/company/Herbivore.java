package com.company;

public class Herbivore extends Animal {
    @Override
    public boolean Eat(Organism Eatit) {
        if (Eatit.x==x && Eatit.y==y && Eatit!=null && Eatit.getClass().getSuperclass()== Plants.class ){
            int a=(100-Wanttoeat)*Size;
            int b=Eatit.Health*Eatit.Size;
            if (b-a>0){
                Wanttoeat=100;
                Eatit.Health-=(int)((a)/Eatit.Size);
                return true;
            }
            else{
                Wanttoeat+=(int)Eatit.Health*Eatit.Size/Size;
                Eatit.Health=0;
                return true;
            }
        }
        return false;
    }//покушать

    @Override
    public Organism FindEat() {
        int[] SetAns=BorderStep();
        int Leftx=SetAns[0], Lefty=SetAns[1];
        int Rightx=SetAns[2], Righty=SetAns[3];
        for (int i = Leftx; i < Rightx; i++)
            for (int j = Lefty; j < Righty; j++) {
            Organism a=Field.Board[i][j].FindOrganism(Plants.class);
                if (Field.Board[i][j] != null &&  a!= null) {
                    return a;
                }
            }
        return null;
    }// поиск еды в пределах видимости
}
