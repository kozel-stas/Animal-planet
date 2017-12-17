package com.company;

public class Field {
    public static int N=10,M=10;//размеры
    public static OrganismCube[][] Board=new OrganismCube[N][M];//поле
    public static void Init(){
        for (int i=0;i<Field.N;i++)
            for(int j=0;j<Field.M;j++){
            OrganismCube a=new OrganismCube();
            Board[i][j]=a;
            }
    }//инитиализация (обязательна!!)
}
