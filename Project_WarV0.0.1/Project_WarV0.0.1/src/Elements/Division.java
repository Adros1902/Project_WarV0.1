package Elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Division extends Place {
    public boolean team;
    public int id;
    public Division(int x, int y, int mode, int typeOfTank, int typeOfSoldier, int amountOfTanks, int amountOfSoldiers, boolean team, int id){
        super(x,y,mode);
        this.team=team;
        this.id=id;
        for(int i=0;i<amountOfTanks;i++){
            Unit tank = new Unit(typeOfTank+3);
            units.add(tank);
        }
        for(int i=0;i<amountOfSoldiers;i++){
            Unit soldier = new Unit(typeOfSoldier);
            units.add(soldier);
        }
        Collections.shuffle(units);
    }
    public ArrayList<Unit> units = new ArrayList<>();



}
