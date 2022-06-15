package Setup;

import Elements.Place;
import Setup.Battlefield;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Input {
    int width;
    int height;
    int villages;
    int minefield;
    int division;
    Place place[][] = new Place[height][width];
    List<Integer> arrayTanks = new ArrayList<>();
    List<Integer> arraySoldiers = new ArrayList<>();
    List<Integer> arrayTypeSoldiers = new ArrayList<>();
    List<Integer> arrayTypeTanks = new ArrayList<>();
    List<Integer> arrayTeam = new ArrayList<>();

    Input(){
        input();
        Battlefield battlefield = new Battlefield( width, height, villages, minefield, division, arrayTanks, arraySoldiers, arrayTypeSoldiers, arrayTypeTanks, arrayTeam);
    }

    public void input(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj wysokość planszy:");
        height = scanner.nextInt();
        System.out.println("Podaj szerokość planszy");
        width = scanner.nextInt();
        place = new Place[height][width];
        System.out.println("Podaj ilość pól minowych:");
        minefield = scanner.nextInt();
        System.out.println("Podaj ilość wiosek:");
        villages = scanner.nextInt();
        System.out.println("Podaj całkowitą ilość dywizji:");
        division = scanner.nextInt();
        System.out.println("Schemat wyboru dla dywizji: \nCyfra jeden - drużyna niebieska, Cyfra 2 - drużyna czerwona\nWybór żołnierzy: cyfra 1 - (50hp, broń: m4a1 - 20 dmg),  " +
                "cyfra 2 - (100hp, broń: AK-47 - 15 dmg),  cyfra 3 - (150hp, broń: glock-17 - 10dmg)\nWybór czołgów: cyfra 1 - (T-55 - 2000hp, 100dmg)" +
                "  cyfra 2 - (M1 Abrams - 1500hp, 150 dmg)\nSchemat zapisu informacji");
        for(int i=0;i<division;i++){
            System.out.println("Wybierz drużynę dywizji "+ (i+1)+":");
            arrayTeam.add(i, scanner.nextInt());
            System.out.println("Wybierz liczbę żołnierzy: ");
            arraySoldiers.add(i, scanner.nextInt());
            System.out.println("Wybierz typ żołnierzy: ");
            arrayTypeSoldiers.add(i, scanner.nextInt());
            System.out.println("Wybierz ilość czołgów: ");
            arrayTanks.add(i, scanner.nextInt());
            System.out.println("Wybierz typ czołgów:");
            arrayTypeTanks.add(i, scanner.nextInt());
        }
    }
}
