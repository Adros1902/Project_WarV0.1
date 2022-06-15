package Elements;

public class Unit {
    public int hp;
    public int damage;

    Unit(int typeOfUnit) {

        switch(typeOfUnit){
            case 1:{
                hp = 50;
                damage = 20;
                break;
            }
            case 2:{
                hp = 100;
                damage = 15;
                break;
            }
            case 3:{
                hp = 150;
                damage = 10;
                break;
            }
            case 4:{
                this.hp = 1500;
                this.damage = 150;
                break;
            }
            default:{
                this.hp = 2000;
                this.damage = 100;
            }
        }
    }
}
