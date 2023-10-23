import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<int[]> Cottages = new ArrayList<>();


    static int cottageCount;
    static int cottageCapacity = 4;

    public static void main(String[] args) {
        System.out.println("SPRÁVA CHATEK");
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
}