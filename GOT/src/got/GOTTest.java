package got;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Scanner;

public class GOTTest {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        HashMap<Integer, String> map = new HashMap<>();

        File names = new File("names.txt");
        names.createNewFile();
        File edges = new File("Got.txt");
        edges.createNewFile();

        addNamesToHashMap(map, names);
        Object[] nameList = new Object[map.size()];
        addNamesToList(map, nameList);

        Graph gotGraph = new Graph(nameList);
        gotGraph.ReadFileGOTGraph(edges);

        System.out.println("///////////////");
        System.out.println("Welcome...");
        menu();

        System.out.print("Enter option: ");
        int option = input.nextInt();

        while (option < 9 && option > 0) {
            switch (option) {

                case 1:
                    System.out.print("Enter letter: ");
                    String firstLetter = input.next();
                    char first = firstLetter.charAt(0);
                    gotGraph.namesWithFirstLetter(names, first);
                    break;

                case 2:
                    System.out.print("Enter low value : ");
                    int low = input.nextInt();
                    System.out.print("Enter high value : ");
                    int high = input.nextInt();
                    gotGraph.namesBetweenWeight(low, high);
                    break;

                case 3:
                    System.out.print("Enter first name : ");
                    String name1 = input.next();
                    System.out.print("Enter second name : ");
                    String name2 = input.next();
                    System.out.print("Enter new weight value : ");
                    int newWeight = input.nextInt();
                    gotGraph.change(name1, name2, newWeight);

                    break;

                case 4:
                    System.out.print("Enter first name : ");
                    String character1 = input.next();
                    System.out.print("Enter second name : ");
                    String character2 = input.next();
                    gotGraph.delete(character1, character2);
                    break;

                case 5:
                    System.out.print("Enter first name : ");
                    String chr1 = input.next();
                    System.out.print("Enter second name : ");
                    String chr2 = input.next();
                    gotGraph.isconnected(chr1, chr2);
                    break;

                case 6:
                    System.out.print("Enter name : ");
                    String name_1 = input.next();
                    System.out.print("Enter thresold value : ");
                    int thresold = input.nextInt();
                    gotGraph.printFartherCharacters(name_1, thresold);
                    break;

                case 7:
                    System.out.print("Enter name : ");
                    String name_2 = input.next();
                    System.out.print("Enter thresold value : ");
                    int thresold1 = input.nextInt();
                    gotGraph.printClosesestCharacters(name_2, thresold1);
                    break;

                case 8:
                    gotGraph.NumberofcharacterGroups();
                    break;

            }
            System.out.println("");
            menu();
            System.out.print("Enter option: ");
            option = input.nextInt();
        }
        if (option == 9) {
            System.out.println("Ok,good bye.");
        }

    }

    public static void menu() {
        System.out.println("Enter 1 to search by the first letter of the name ");
        System.out.println("Enter 2 to print names in the range of weight values entered ");
        System.out.println("Enter 3 to write new weight ");
        System.out.println("Enter 4 to delete characters ");
        System.out.println("Enter 5 to find out if two characters are connected ");
        System.out.println("Enter 6 to see the characters with the value above the threshold value you will enter ");
        System.out.println("Enter 7 to see the characters with the value below the threshold value you will enter ");
        System.out.println("Enter 8 to see linked groups ");
        System.out.println("Enter 9 to quit");
    }

    public static void addNamesToList(HashMap map, Object[] obj) {
        for (int i = 0; i < map.size(); i++) {
            obj[i] = map.get(i);
        }
    }

    public static void addNamesToHashMap(HashMap map, File names) {
        int nameInx = 0;
        Scanner scanner;
        try {
            FileReader fileReader = new FileReader(names);
            scanner = new Scanner(new BufferedReader(fileReader));
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                map.put(nameInx, name);
                nameInx++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
