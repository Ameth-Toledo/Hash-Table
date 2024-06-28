import models.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String line;
        String splitBy = ",";
        int id = 1;

        HashTable<String, String[]> hashTableSquared = new HashTable<>();
        HashTable<String, String[]> hashTableMultiplied = new HashTable<>();

        long startTimeSquared = 0;
        long endTimeSquared = 0;
        long startTimeMultiplied = 0;
        long endTimeMultiplied = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("bussines.csv"))) {
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                System.out.println("[" + id + "] Business [ID=" + business[0] + ", Name=" + business[1] + ", Address=" + business[2] + ", City=" + business[3] + ", State= " + business[4] + "]");
                id++;
                String key = business[0];

                startTimeSquared = System.nanoTime();
                hashTableSquared.put(key, business, true);
                endTimeSquared = System.nanoTime();

                startTimeMultiplied = System.nanoTime();
                hashTableMultiplied.put(key, business, false);
                endTimeMultiplied = System.nanoTime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        double totalTimeSquaredInSeconds = (endTimeSquared - startTimeSquared) / 1_000_000_000.0;
        double totalTimeMultipliedInSeconds = (endTimeMultiplied - startTimeMultiplied) / 1_000_000_000.0;

        double totalTimeSquaredInMinutes = totalTimeSquaredInSeconds / 60;
        double totalTimeMultipliedInMinutes = totalTimeMultipliedInSeconds / 60;

        System.out.println("Tiempo total para metodo de elevacion al cuadrado: " + totalTimeSquaredInMinutes + " minutos.");
        System.out.println("Tiempo total para metodo por multiplicacion: " + totalTimeMultipliedInMinutes + " minutos.");
    }
}