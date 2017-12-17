package com.company;

import com.sun.org.apache.xpath.internal.operations.Or;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Emulation {
    private static ArrayList <Animal> Setofanimal=new ArrayList<Animal>();
    private static ArrayList <Plants> Setofplants=new ArrayList<Plants>();

    private static void EmulationAnimal(){
        for (int i=0;i<Setofanimal.size();i++) {
            if (Setofanimal.get(i).Wanttoeat>50){
                if (Setofanimal.get(i).Wantreproduction>50){
                    Setofanimal.get(i).RandGo();
                    Animal.Stroke(Setofanimal.get(i),Setofanimal.get(i).getWantX(),Setofanimal.get(i).getWantY());
                    Setofanimal.get(i).setWantY(-1);
                    Setofanimal.get(i).setWantX(-1);
                }
                else{
                    if (Setofanimal.get(i).Couple!=null){
                        if(Setofanimal.get(i).x==Setofanimal.get(i).getWantX() && Setofanimal.get(i).y==Setofanimal.get(i).getWantY()){
                            if (Setofanimal.get(i).Couple.x==Setofanimal.get(i).getWantX() && Setofanimal.get(i).Couple.y==Setofanimal.get(i).getWantY()){
                                Animal b= Animal.Reproduction(Setofanimal.get(i),Setofanimal.get(i).Couple);
                                if (b!=null) Add(b);
                            }
                        }
                        else{
                            Animal.Stroke(Setofanimal.get(i), Setofanimal.get(i).getWantX(), Setofanimal.get(i).getWantY());
                        }
                    }
                    else {
                        if (Setofanimal.get(i).FindRepObject(Setofanimal.get(i).getClass())) {
                            if(Setofanimal.get(i).x==Setofanimal.get(i).getWantX() && Setofanimal.get(i).y==Setofanimal.get(i).getWantY()){
                                if (Setofanimal.get(i).Couple.x==Setofanimal.get(i).getWantX() && Setofanimal.get(i).Couple.y==Setofanimal.get(i).getWantY()){
                                    Animal.Reproduction(Setofanimal.get(i),Setofanimal.get(i).Couple);
                                }
                            }
                            else{
                                Animal.Stroke(Setofanimal.get(i), Setofanimal.get(i).getWantX(), Setofanimal.get(i).getWantY());
                            }
                        } else {
                            Setofanimal.get(i).RandGo();
                            Animal.Stroke(Setofanimal.get(i), Setofanimal.get(i).getWantX(), Setofanimal.get(i).getWantY());
                            Setofanimal.get(i).setWantY(-1);
                            Setofanimal.get(i).setWantX(-1);
                        }
                    }
                }
            }
            else{
                if (Setofanimal.get(i).Wanttoeat==0) Setofanimal.get(i).StepHealth*=2;
                Organism perm=Setofanimal.get(i).FindEat();
                if (perm==null){
                    Setofanimal.get(i).RandGo();
                    Animal.Stroke(Setofanimal.get(i),Setofanimal.get(i).getWantX(),Setofanimal.get(i).getWantY());
                    Setofanimal.get(i).setWantY(-1);
                    Setofanimal.get(i).setWantX(-1);
                }
                else {
                    if (perm.x == Setofanimal.get(i).x && perm.y == Setofanimal.get(i).y) {
                        Setofanimal.get(i).Eat(perm);
                        if (perm.Health==0 && perm.getClass().getSuperclass().getSuperclass()==Animal.class) Dead((Animal)perm);
                        else if (perm.Health==0 && perm.getClass().getSuperclass()==Plants.class) Dead((Plants)perm);
                    }
                    else
                        Animal.Stroke(Setofanimal.get(i),perm.x,perm.y);
                }
            }
            Setofanimal.get(i).Starving();
            Setofanimal.get(i).Aging();
            Setofanimal.get(i).Rep();
            if (Setofanimal.get(i).Health==0) Dead(Setofanimal.get(i));
        }
    }//эмуляция животных(порядок еда размножение бродит)

    private static void EmulationPlants(){
        for (int i=0;i<Setofplants.size();i++){
            if (Setofplants.get(i).Wantreproduction<50){
                Plants b=Plants.Reproduction(Setofplants.get(i));
                if (b!=null) Add(b);
            }
            Setofplants.get(i).Rep();
            Setofplants.get(i).Aging();
        }
    }//эмуляция растений

    public static void Step() {
        Print();
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        switch (a) {
            case 1:
                EmulationAnimal();
                EmulationPlants();
                break;
            case 2:
                System.exit(0);
                break;
        }

    }// основа шагов

    private static void Dead (Animal Deadit){
        Field.Board[Deadit.x][Deadit.y].DellOrganism((Organism) Deadit);
        Setofanimal.remove(Deadit);
    }//смерть животным

    private static void Dead (Plants Deadit){
        Field.Board[Deadit.x][Deadit.y].DellOrganism((Organism)Deadit);
        Setofplants.remove(Deadit);
    }//смерть растениям

    public static void Add (Animal a){
        Setofanimal.add(a);
    }//добавление животного

    public static void Add (Plants a){
        Setofplants.add(a);
    }//Добавление растения

    public static void Print(){
        try {
            FileWriter writer = new FileWriter(new File(("AnimalPlanet.txt")));
            for (int i = 0; i < Field.N; i++) {
                for (int g = 0; g < 3; g++) {
                    for (int j = 0; j < Field.M; j++) {

                        if (Field.Board[i][j].getSetofOrganizm()[g] == null)
                            writer.write("null    ");
                        else writer.write(Field.Board[i][j].getSetofOrganizm()[g].Name + "  ");
                    }
                    writer.write("/n");
                }
                writer.append("\n");
                writer.append("\n");
            }
            writer.append("\n");
            writer.close();
        }
        catch (IOException a){}
    }// печать в консоль
}
