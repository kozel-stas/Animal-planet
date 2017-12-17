package com.company;

import java.util.ArrayList;
import java.util.Random;

public class Animal extends Organism {
    private int WantX = -1, WantY = -1;//куда хочет походить животное
    protected boolean Sex;//пол
    protected int Wanttoeat=100;
    protected int Stepeat;
    protected int How_much_cells_you_see;//ход и количество ячеек осмотра окрестностей
    protected Animal Couple=null;//пара для размножения

    public int getWantX() {
        return WantX;
    }

    public int getWantY() {
        return WantY;
    }

    public void setWantX(int wantX) {
        WantX = wantX;
    }

    public void setWantY(int wantY) {
        WantY = wantY;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    protected int[] BorderStep(){
        int Leftx, Lefty;
        int Rightx, Righty;
        if (x - How_much_cells_you_see >= 0)
            Leftx = x - How_much_cells_you_see;
        else
            Leftx = 0;
        if (y - How_much_cells_you_see >= 0)
            Lefty = y - How_much_cells_you_see;
        else
            Lefty = 0;
        if (x + How_much_cells_you_see <= Field.N)
            Rightx = x + How_much_cells_you_see;
        else {
            int st = 0;
            while (x + How_much_cells_you_see - st > Field.N)
                st++;
            Rightx = x + How_much_cells_you_see - st;
        }
        if (y + How_much_cells_you_see <= Field.M)
            Righty = y + How_much_cells_you_see;
        else {
            int st = 0;
            while (y + How_much_cells_you_see - st > Field.N)
                st++;
            Righty = y + How_much_cells_you_see - st;
        }
        int []ans={Leftx,Lefty,Rightx,Righty};
        return ans;
    }//граница осмотра и хода

    public boolean FindRepObject(Class FindIt) {
        int[] SetAns=BorderStep();
        int Leftx=SetAns[0], Lefty=SetAns[1];
        int Rightx=SetAns[2], Righty=SetAns[3];
        for (int i = Leftx; i < Rightx; i++)
            for (int j = Lefty; j < Righty; j++) {
                if (Field.Board[i][j] != null && Field.Board[i][j].FindOrganism(FindIt,Sex)!=null) {
                    Couple=(Animal) Field.Board[i][j].FindOrganism(FindIt,Sex);
                    Couple.Couple=this;
                    for (int k = Leftx; k < Rightx; k++)
                        for (int z = Lefty; z < Righty; z++) {
                            if(Field.Board[k][z].Howmanyplace()==3) {
                                WantX = k;
                                Couple.WantX = k;
                                WantY = z;
                                Couple.WantY = z;
                            }
                        }
                    return true;
                }
            }
        return false;
    }// поиск партнера

    public static boolean Stroke(Animal a,int x1,int y1){
        int[] SetAns=a.BorderStep();
        int Leftx=SetAns[0], Lefty=SetAns[1];
        int Rightx=SetAns[2], Righty=SetAns[3];
        if (x1>=Leftx &&x1<=Rightx && y1>=Lefty && y1<=Righty && Field.Board[x1][y1].HavePlace()==true){
            Animal nu=(Animal)Field.Board[a.x][a.y].FindOrganism(Predator.class);
            if(a.getClass().getSuperclass()==Herbivore.class && nu!=null ) {
                return false;
            }else{
                Field.Board[x1][y1].AddOrganism(a);
                Field.Board[a.x][a.y].DellOrganism(a);
                a.x = x1;
                a.y = y1;
                return true;
            }
        }
        return false;
    }//ХОД

    public static Animal Reproduction(Animal par1,Animal par2){
        if(par1.x==par2.x && par1.y==par2.y && Field.Board[par1.x][par1.y].Howmanyplace()==1){
            if (par1.getClass()==par2.getClass() && !par1.Sex==par2.Sex){
                Animal b=(Animal)par1.Clone();
                par1.Couple=null;
                par2.Couple=null;
                par1.setWantX(-1);
                par1.setWantY(-1);
                par2.setWantX(-1);
                par2.setWantY(-1);
                par1.Wantreproduction=100;
                par2.Wantreproduction=100;
                return b;
            }
        }
        return null;
    }//размножение

    public Organism FindEat(){Organism a=new Organism(); return a;}//прототип

    public boolean Eat(Organism Eatit) {
        return true;
    }//прототип

    public boolean Starving(){
        if (Wanttoeat-Stepeat>0) {
            Wanttoeat -= Stepeat;
            return true;
        }
        else{
            Wanttoeat=0;
            return false;
        }
    }//голодание

    public void RandGo() {
        int[] ans = BorderStep();
        int[][] angles = new int[4][2];
        angles[0][0] = ans[0];
        angles[0][1] = ans[1];
        angles[1][0] = ans[2] - 1;
        angles[1][1] = ans[1];
        angles[2][0] = ans[0];
        angles[2][1] = ans[3] - 1;
        angles[3][0] = ans[2] - 1;
        angles[3][1] = ans[3] - 1;
        ArrayList<int[]> list = new ArrayList<int[]>(4);
        for (int i = 0; i < 4; i++) {
            if (Field.Board[angles[i][0]][angles[i][1]].HavePlace() == true)
                list.add(angles[i]);
        }
        Random rand = new Random();
        int num = list.size();
        int per;
        if (num > 0) {
            do {
                per = rand.nextInt(num);
            } while (list.get(per)[0] == x && list.get(per)[1] == y );
            WantX = angles[per][0];
            WantY = angles[per][1];
        } else {
            int tempX,tempY;
            do {
                tempX = rand.nextInt(ans[2] - ans[0] + 1) + ans[0];
                tempY = rand.nextInt(ans[3] - ans[1] + 1) + ans[1];
            }while (tempX==x && tempY==y && Field.Board[tempX][tempY].HavePlace()==true);
            WantX=tempX;
            WantY=tempY;
        }

    }//рандомная координата для смещения
}
