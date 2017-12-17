package com.company;

public class Plants extends Organism {

    public static Plants Reproduction(Plants a){
        int [] ans=Field.Board[a.x][a.y].HavePlantsplace(a.x,a.y);
        if (ans!=null) {
            Plants b=(Plants)a.Clone(ans);
            a.Wantreproduction=100;
            return b;
        }
        return null;
    }//размножение

    public Organism Clone(int[] ans) {
        return super.Clone();
    }//клонирование(рождение дитяти)
}
