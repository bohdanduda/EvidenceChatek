import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    static List<int[]> Cottages = new ArrayList<>();


    static int cottageCount;
    static int cottageCapacity;

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

    public static int getPeopleCount()
    {
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
}