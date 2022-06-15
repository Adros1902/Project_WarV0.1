package Setup;

import Elements.Division;
import Elements.Place;
import Elements.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battlefield {
    int width;
    int height;
    int villages;
    int minefield;
    int division;
    boolean win=false;
    int battles = 0;
    Place[][] place;
    List<Division> dywizje = new ArrayList<>();
    List<Integer> arrayTanks;
    List<Integer> arraySoldiers;
    List<Integer> arrayTypeSoldiers;
    List<Integer> arrayTypeTanks;
    List<Integer> arrayTeam;
    List<Division> toRemove = new ArrayList<>();

    Battlefield(int width, int height, int villages, int minefield, int division, List<Integer> arrayTanks, List<Integer> arraySoldiers, List<Integer> arrayTypeSoldiers, List<Integer> arrayTypeTanks, List<Integer> arrayTeam){
        this.width = width;
        this.height = height;
        this.villages = villages;
        this.minefield = minefield;
        this.division = division;
        this.arrayTanks = arrayTanks;
        this.arraySoldiers = arraySoldiers;
        this.arrayTypeSoldiers = arrayTypeSoldiers;
        this.arrayTypeTanks = arrayTypeTanks;
        this.arrayTeam = arrayTeam;
        place = new Place[height][width];
        setupBattlefield();
    }


    public void setupBattlefield(){
        generateBattlefield();
        int dayCounter=0;
        while(!win){
            moveDivisions();
            boolean team1=false;
            boolean team2=false;
            for(Division division : dywizje){
                if(!division.team){
                    team1=true;
                }else {team2=true;}
            }
            dayCounter++;
            System.out.println("Dzien: "+dayCounter);
            System.out.println("Ilosc pozostalych dywizji: "+dywizje.size());
            if(!team1 || !team2){
                win=true;
                System.out.println("Wojna trwala "+dayCounter+" dni");
                if(!team1){
                System.out.println("Wojne wygrala druzyna czerwonych");
                } else {
                    System.out.println("Wojne wygrala druzyna niebieskich");
                }
            }
        }
        System.out.print("Bitwy:"+battles);
    }
    public void generateBattlefield(){
        Random random = new Random();
        for(int y=0;y<height;y++) {
            for(int x=0;x<width;x++){
                place[y][x]=new Place(x,y,0);
            }
        }
        for(int i=0;i<villages;i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            if(place[randomY][randomX].mode == 0){
                place[randomY][randomX] = new Place(randomX,randomY,1);
            }
        }
        for(int i=0;i<minefield;i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            if(place[randomY][randomX].mode == 0 ){
                place[randomY][randomX] = new Place(randomX,randomY,2);
            }
        }
        int counter3=0;
        for(int i=0;i<division;i++) {
            int randomX = random.nextInt(width);
            int randomY = random.nextInt(height);
            if (place[randomY][randomX].mode == 0 ) {
                boolean temp;
                switch(arrayTeam.get(i)){
                    case 1:{
                        temp=false;
                        break;
                    }
                    default:{
                        temp=true;
                    }
                }
                counter3++;
                Division division = new Division(randomX,randomY,3,arrayTypeTanks.get(i),arrayTypeSoldiers.get(i),arrayTanks.get(i),arraySoldiers.get(i),temp,counter3);
                dywizje.add(division);
                place[randomY][randomX] = division;
            }else i--;
        }
    }
    public void moveDivisions(){
        Random random = new Random();
        for(Division division : dywizje){
            if(!toRemove.contains(division)){
                int randomNumber = random.nextInt(9);
                switch (randomNumber){
                    case 0:{
                        if(division.x>0 && division.y>0 && !(place[division.y-1][division.x-1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x--;
                            division.y--;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 1:{
                        if(division.x<width-1 && division.y<height-1 && !(place[division.y+1][division.x+1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x++;
                            division.y++;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 2:{
                        if(division.x>0 && division.y<height-1 && !(place[division.y+1][division.x-1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x--;
                            division.y++;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 3:{
                        if(division.x<width-1 && division.y>0 && !(place[division.y-1][division.x+1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x++;
                            division.y--;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 4:{
                        if(division.x>0 && !(place[division.y][division.x-1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x--;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 5:{
                        if(division.x<width-1  && !(place[division.y][division.x+1].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.x++;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 6:{
                        if(division.y>0 && !(place[division.y-1][division.x].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.y--;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    case 7:{
                        if(division.y<height-1 && !(place[division.y+1][division.x].mode == 1)){
                            place[division.y][division.x] = new Place(division.x, division.y,0);
                            division.y++;
                            divisionMoved(division);
                            place[division.y][division.x] = division;
                            break;
                        }}
                    default: break;
                }
            }
        }
        for(Division division : toRemove){
            dywizje.remove(division);
        }
    }
    public void divisionMoved(Division division){
        int x = division.x;
        int y = division.y;
        System.out.println("Dywizja nr "+division.id+" pozycja: x= "+x+" y= "+y);
        if(place[y][x].mode == 2){
            int damageTaken=0;
            for(Unit unit : division.units){
                damageTaken += (unit.hp * 0.2);
                unit.hp *= 0.8;
            }
            System.out.println("Dywizja nr:"+division.id+" weszla w pole minowe, na polu: x= "+x+" y= "+y+"\n" +
                    "Zadane obrazenia: "+damageTaken);
        }
        if(place[y][x] instanceof Division){
            Division division2 = (Division) place[y][x];
            if((division2).team == division.team){
                if(!(toRemove.contains(division) || toRemove.contains(division2))){
                    System.out.println("Dywizja nr "+division2.id+"dolacza do dywizji nr "+division.id);
                    mergeDivisions(division,(Division)place[y][x]);
                }
            }else{
                if(!(toRemove.contains(division) || toRemove.contains(division2))){
                    battle(division,(Division)place[y][x]);
                }
            }
        }
    }
    public void mergeDivisions(Division division, Division division2){
        for(Unit unit : division2.units){
            division.units.add(unit);
        }
        toRemove.add(division2);
    }
    public void battle(Division division, Division division2){
        System.out.println("Bitwa pomiedzy dywizja nr "+division.id+" i dywizja nr "+division2.id);
        battles++;
        boolean fighting=true;
        int turnCounter=0;
        System.out.println("Dywizja nr "+division.id+" ma "+division.units.size()+" jednostek");
        System.out.println("Dywizja nr "+division2.id+" ma "+division2.units.size()+" jednostek");
        while(fighting){
            turnCounter++;
            System.out.println("Tura nr: "+turnCounter);
            int counter1 = 0;
            int counter2 = 0;
            int counterHp1 = 0;
            int counterHp2 = 0;
            for(Unit unit : division2.units){
                counter2 += unit.damage;
                counterHp2 += unit.hp;
            }
            for(Unit unit : division.units){
                counter1 += unit.damage;
                counterHp1 += unit.hp;
            }
            System.out.println("Obrazenia dywizji nr "+division.id+ " = "+counter1);
            System.out.println("Obrazenia dywizji nr "+division2.id+ " = "+counter2);
            System.out.println("Zdrowie dywizji nr "+division.id+" = " + counterHp1);
            System.out.println("Zdrowie dywizji nr "+division2.id+" = " + counterHp2);
            List<Unit> unitsToRemove1 = new ArrayList<>();
            for(Unit unit : division2.units){
                if(unit.hp<counter1){
                    counter1 -= unit.hp;
                    unitsToRemove1.add(unit);
                }else {
                    unit.hp -= counter1;
                }
            }
            for(Unit unit : unitsToRemove1){
                division2.units.remove(unit);
            }
            List<Unit> unitsToRemove2 = new ArrayList<>();
            for(Unit unit : division.units){
                if(unit.hp<counter2){
                    counter2 -= unit.hp;
                    unitsToRemove2.add(unit);
                }else{
                    unit.hp -= counter2;
                }
            }
            for(Unit unit : unitsToRemove2){
                division.units.remove(unit);

            }
            System.out.println("Dla dywizji nr "+division.id+" pozostalo "+division.units.size()+" jednostek");
            System.out.println("Dla dywizji nr "+division2.id+" pozostalo "+division2.units.size()+" jednostek");
            if(division.units.isEmpty() || division2.units.isEmpty()){
                fighting=false;
            }
        }
        if(division.units.isEmpty()){
            toRemove.add(division);
            if(division2.units.isEmpty()){
                System.out.println("Wszyscy zgineli");
                toRemove.add(division2);
            }else{
                System.out.println("Bitwe zwyciezyla dywizja nr "+division2.id);
            }
        }else {
            System.out.println("Bitwe zwyciezyla dywizja nr "+division.id);
            toRemove.add(division2);
        }
    }
}
