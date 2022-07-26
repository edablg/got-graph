package got;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Scanner;

public class Graph {

    int[][] edges;
    int numV;
    Object[] myList;

    Graph(Object[] list) {
        this.numV = list.length;
        myList = list;
        edges = new int[list.length][list.length];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                edges[i][j] = 0;
            }
        }
    }

    public void NumberofcharacterGroups() {
        for (int i = 0; i < myList.length; i++) {
            Object[] store = new Object[myList.length];
            int inxStore = 0;
            Object name = myList[i];
            store[inxStore] = name;
            inxStore++;
            name = findConnection(name);

            while (name != null) {
                store[inxStore] = name;
                inxStore++;
                name = findConnection(name);
            }
            if (inxStore > 1) {
                System.out.print("Component" + i + ": ");
                for (int j = 0; j < inxStore; j++) {
                    System.out.print(store[j] + ",");
                }
                System.out.println("");
            }
        }
    }

    public Object findConnection(Object name) {
        int indexName = -1;
        for (int i = 0; i < myList.length; i++) {
            if (this.myList[i].equals(name)) {
                indexName = i;
            }
        }
        for (int i = 0; i < myList.length; i++) {
            if (edges[indexName][i] != 0) {
                return myList[i];
            }
        }
        return null;
    }

    public void namesWithFirstLetter(File file, Character firstL) {
        Scanner scanner;
        try {
            FileReader fileReader = new FileReader(file);
            scanner = new Scanner(new BufferedReader(fileReader));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.charAt(0) == firstL) {
                    System.out.println(line);
                }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void namesBetweenWeight(int low, int high) {
        for (int i = 0; i < myList.length; i++) {
            for (int j = 0; j < myList.length; j++) {
                if (edges[i][j] > low && edges[i][j] < high) {
                    System.out.println(myList[i] + " and " + myList[j]);

                }
            }
        }
    }

    public void change(String name1, String name2, int newWeight) {
        int indexName1 = -1;
        int indexName2 = -1;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(name1)) {
                indexName1 = i;
            }
            if (myList[i].equals(name2)) {
                indexName2 = i;
            }
        }
        if (edges[indexName1][indexName2] != 0) {
            System.out.println("Old weight between " + name1 + " and " + name2 + ": " + edges[indexName1][indexName2]);
            edges[indexName1][indexName2] = newWeight;
            displayForChange(name1, name2);
        } else if (edges[indexName2][indexName1] != 0) {
            System.out.println("Old weight between " + name2 + " and " + name1 + ": " + edges[indexName2][indexName1]);
            edges[indexName2][indexName1] = newWeight;
            displayForChange(name2, name1);
        } else {
            System.out.println("There is no connection between them!");
        }

    }

    public void delete(String name1, String name2) {
        int indexName1 = -1;
        int indexName2 = -1;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(name1)) {
                indexName1 = i;
            }
            if (myList[i].equals(name2)) {
                indexName2 = i;
            }
        }
        if (edges[indexName1][indexName2] != 0 || edges[indexName2][indexName1] != 0) {
            edges[indexName1][indexName2] = 0;
            edges[indexName2][indexName1] = 0;
            System.out.println("Deletion is completed");
        } else {
            System.out.println("There is no connection between them already.");
        }

    }

    public void isconnected(String name1, String name2) {
        int indexName1 = -1;
        int indexName2 = -1;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(name1)) {
                indexName1 = i;
            }
            if (myList[i].equals(name2)) {
                indexName2 = i;
            }
        }
        if (edges[indexName1][indexName2] != 0 || edges[indexName2][indexName1] != 0) {

            System.out.println("These two has a connection");
        } else {

            System.out.println("There is no connection between them!");
        }
    }

    public void printFartherCharacters(String name, int threshold) {
        int indexName = -1;
        for (int i = 0; i < myList.length; i++) {
            if (this.myList[i].equals(name)) {
                indexName = i;
            }
        }

        for (int i = 0; i < myList.length; i++) {
            if (edges[indexName][i] >= threshold) {
                System.out.println(myList[i]);
                System.out.println(edges[indexName][i]);
            }
        }
    }

    public void printClosesestCharacters(String name, int threshold) {
        int indexName = -1;
        for (int i = 0; i < myList.length; i++) {
            if (this.myList[i].equals(name)) {
                indexName = i;
            }
        }

        for (int i = 0; i < myList.length; i++) {
            if (edges[indexName][i] <= threshold && edges[indexName][i] != 0) {
                System.out.println(myList[i]);

            }
        }
    }

    public void addEdge(String from, String to, int weight) {
        int indexFrom = -1;
        int indexTo = -1;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(from)) {
                indexFrom = i;
            }
            if (myList[i].equals(to)) {
                indexTo = i;
            }
        }
        if (indexFrom != -1 && indexTo != -1) {
            edges[indexFrom][indexTo] = weight;
        }
    }

    public void ReadFileGOTGraph(File got) {
        String[] split = new String[3];
        Scanner scanner;
        try {
            FileReader fileReader = new FileReader(got);
            scanner = new Scanner(new BufferedReader(fileReader));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                split = line.split(",");
                int weight = Integer.parseInt(split[2]);

                addEdge(split[0], split[1], weight);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void displayForChange(String name1, String name2) {
        int indexName1 = -1;
        int indexName2 = -1;
        for (int i = 0; i < myList.length; i++) {
            if (myList[i].equals(name1)) {
                indexName1 = i;
            }
            if (myList[i].equals(name2)) {
                indexName2 = i;
            }
        }
        System.out.println("New connection between " + name1 + " and " + name2 + ": " + edges[indexName1][indexName2]);

    }

}
