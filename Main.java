import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<int[]> Cottages = new ArrayList<>();


    static int cottageCount;
    static int cottageCapacity;

    public static void main(String[] args) {
        getCottages();

        getUserInterface();
    }

    public static void getCottages() {
        System.out.print("Kolik chatek je celkem k dispozici?: ");
        cottageCount = scanner.nextInt();

        System.out.print("Jaká je kapacita chatky?: ");
        cottageCapacity = scanner.nextInt();

        for (int i = 1; i <= cottageCount; i++) {
            int[] cottage = new int[cottageCapacity];

            Cottages.add(cottage);
        }
    }

    public static int getPeopleCount() {
        int cottageIndex = 0;
        int peopleCounter = 0;

        while (cottageIndex < Cottages.size()) {
            for (int person : Cottages.get(cottageIndex)) {
                peopleCounter += person;
            }
            cottageIndex++;
        }

        return peopleCounter;
    }

    public static void getEmptyCottages() {
        int cottageIndex = 0;
        List<Integer> emptyCottages = new ArrayList<Integer>();

        while (cottageIndex < Cottages.size()) {
            int peopleCounter = 0;

            for (int person : Cottages.get(cottageIndex)) {
                peopleCounter += person;
            }

            if (peopleCounter == 0) {
                emptyCottages.add(cottageIndex);
            }

            cottageIndex++;
        }

        System.out.println("Volné chatky:");
        for (int cottageNumber : emptyCottages) {
            System.out.println("Chata " + (cottageNumber + 1));
        }
    }

    public static int getCottageAvailability(int cottageNumber) {
        int peopleCounter = 0;

        for (int person : Cottages.get(cottageNumber - 1)) {
            peopleCounter += person;
        }

        return cottageCapacity - peopleCounter;
    }

    public static void addPeopleToCottage(int numberOfPeople, int cottageNumber) {
        int peopleCounter = 0;

        for (int person : Cottages.get(cottageNumber - 1)) {
            peopleCounter += person;
        }

        if (numberOfPeople > (cottageCapacity - peopleCounter)) {
            System.out.println("Chata nemá místo pro tolik lidí!");

            System.exit(0);
        }

        int counter = 0;
        for (int i = 0; i < Cottages.get(cottageNumber - 1).length; i++) {
            if (Cottages.get(cottageNumber - 1)[i] == 1) {
                continue;
            }

            if (counter >= numberOfPeople) {
                return;
            }

            Cottages.get(cottageNumber - 1)[i] = 1;
            counter++;
        }
    }

    public static void removePeopleFromCottage(int numberOfPeople, int cottageNumber) {
        int peopleCounter = 0;
        for (int person : Cottages.get(cottageNumber - 1)) {
            peopleCounter += person;
        }

        if (numberOfPeople > (peopleCounter)) {
            System.out.println("Nemůžete odebrat víc lidí než je v chatě!");

            System.exit(0);
        }

        int counter = 0;
        for (int i = 0; i < Cottages.get(cottageNumber - 1).length; i++) {
            if (Cottages.get(cottageNumber - 1)[i] == 0) {
                continue;
            }

            if (counter >= numberOfPeople) {
                return;
            }

            Cottages.get(cottageNumber - 1)[i] = 0;
            counter++;
        }
    }

    public static void getUserInterface() {
        System.out.println("SPRÁVA CHATEK\n");

        System.out.println("\nCo si přejete udělat?");
        System.out.println("1 - Vypsat obsazenost chatek");
        System.out.println("2 - Přidat lidi do chatky");
        System.out.println("3 - Odebrat lidi z chatky");
        System.out.println("4 - Vypsat celkový počet ubytovaných lidí");
        System.out.println("5 - Vypsat seznam prázdných chatek");
        System.out.println("0 - Konec\n");
        int usersChoice = scanner.nextInt();
        System.out.println();
        if (usersChoice < 0 || usersChoice > 5) {
            System.out.println("Zadejte validní odpověď v rozsahu 0-5!");

            getUserInterface();
        }

        if (usersChoice == 1) {
            System.out.print("Zadejte číslo chatky: ");
            int cottageNumber = scanner.nextInt();
            System.out.println("V chatě " + cottageNumber + " zbývá " + getCottageAvailability(cottageNumber) + " volných míst...");

            returnToMenu();
        }
        if (usersChoice == 2) {
            System.out.print("Zadejte číslo chatky: ");
            int cottageNumber = scanner.nextInt();

            System.out.print("Zadejte počet lidí: ");
            int numberOfPeople = scanner.nextInt();

            addPeopleToCottage(numberOfPeople, cottageNumber);

            returnToMenu();
        }
        if (usersChoice == 3) {
            System.out.print("Zadejte číslo chatky: ");
            int cottageNumber = scanner.nextInt();

            System.out.print("Zadejte počet lidí: ");
            int numberOfPeople = scanner.nextInt();

            removePeopleFromCottage(numberOfPeople, cottageNumber);

            returnToMenu();
        }
        if (usersChoice == 4) {
            System.out.println("Celkový počet ubytovaných lidí je: " + getPeopleCount());

            returnToMenu();
        }
        if (usersChoice == 5) {
            getEmptyCottages();

            returnToMenu();
        }

        if (usersChoice == 0) {
            System.out.println("Nashledanou...");
            System.exit(0);
        }
    }

    public static void returnToMenu() {
        System.out.println("\nKlikněte na Enter pro návrat do menu...");
        try {
            System.in.read();
            scanner.nextLine();

            getUserInterface();
        } catch (Exception e) {
        }
    }
}