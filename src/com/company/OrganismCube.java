package com.company;


public class OrganismCube {// основа доски
    private Organism[] SetofOrganizm = new Organism[3];

    public void setSetofOrganizm(Organism[] SetofOrganism) {
        this.SetofOrganizm = SetofOrganism;
    }

    public Organism[] getSetofOrganizm() {
        return SetofOrganizm;
    }

    public Organism FindOrganism(Class findit,boolean a) {
        for (int i = 0; i < 3; i++)
            if (SetofOrganizm[i]!=null)
                if (SetofOrganizm[i].getClass() == findit) {
                    Animal b=(Animal) SetofOrganizm[i];
                    if (b.Sex==!a && b.Wantreproduction<51 && b.Wanttoeat>61)
                        return b;
                }
        return null;
    }//поиск организма схожего класса отлисного пола

    public Organism FindOrganism(Class findit) {
        for (int i = 0; i < 3; i++)
            if (SetofOrganizm[i]!=null)
                if (SetofOrganizm[i].getClass().getSuperclass() == findit)
                        return SetofOrganizm[i];
        return null;
    } //поиск по родителю класса, для поиска еды

    public boolean AddOrganism(Organism AddIt) {
        for (int i = 0; i < 3; i++) {
            if (SetofOrganizm[i] == null) {
                SetofOrganizm[i] = AddIt;
                return true;
            }
        }
        return false;
    }// добавление в массив

    public boolean DellOrganism(Organism DellIt) {
        for (int i = 0; i < 3; i++) {
            if (SetofOrganizm[i] == DellIt) {
                SetofOrganizm[i] = null;
                Sort();
                return true;
            }
        }
        return false;
    }//удаление

    public void Sort() {
        for (int i = 0; i < 3; i++) {
            if (SetofOrganizm[i] == null) {
                for (int j = 2; j >= 0; j--)
                    if (SetofOrganizm[j] !=null)
                        if (i < j) {
                            Organism temp;
                            temp = SetofOrganizm[j];
                            SetofOrganizm[i] = temp;
                            SetofOrganizm[j] = null;
                            break;
                        }
            }
        }
    }// сортировка по пустым ячейкам

    public boolean HavePlace(){
        int sh=0;
        for (int i=0;i<3;i++)
            if (SetofOrganizm[i]!=null)
                sh++;
        if (sh==3) return false;
        else return true;
    }//есть ли место в массиве

    public int Howmanyplace(){
        int sh=0;
        for (int i=0;i<3;i++)
            if (SetofOrganizm[i]==null)
                sh++;
        return sh;
    }// сколько ячеек свободно

    public int[] HavePlantsplace(int x,int y){
        int ans[]=new int[2];
        if (x!=Field.N-1 && Field.Board[x+1][y].HavePlants()==false){
            ans[0]=x+1;
            ans[1]=y;
            return ans;
        }
        if (x!=0 && Field.Board[x-1][y].HavePlants()==false){
            ans[0]=x-1;
            ans[1]=y;
            return ans;
        }
        if (y!=Field.M-1 && Field.Board[x][y+1].HavePlants()==false){
            ans[0]=x;
            ans[1]=y+1;
            return ans;
        }
        if (y!=0 && Field.Board[x][y-1].HavePlants()==false){
            ans[0]=x;
            ans[1]=y-1;
            return ans;
        }
       return ans;
    }//массив свободных клеток около растений

    private boolean HavePlants() {
        for (int i=0;i<3;i++) {
            if(SetofOrganizm[i]!=null)
                if (SetofOrganizm[i] instanceof Plants){
                return true;
                }
        }
        if (HavePlace()==true) return false;
        else return true;
    }//есть ли растения в клетке
}