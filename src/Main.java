import models.HashNode;
import models.HashTable;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<String, String[]> hashTableSquared = new HashTable<>();
        HashTable<String, String[]> hashTableMultiplied = new HashTable<>();

        // Menú para imprimir o buscar
        // Menú para imprimir o buscar
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Imprimir todos los datos");
            System.out.println("2. Buscar por clave");
            System.out.println("3. Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                leerCSVYGuardarEnHashTable("bussines.csv", hashTableSquared, hashTableMultiplied, true);
                imprimirTodosLosDatos(hashTableSquared);
            } else if (opcion == 2) {
                System.out.println("Ingrese la clave a buscar:");
                String clave = scanner.nextLine();
                System.out.print("Buscando clave... ");
                leerCSVYGuardarEnHashTable("bussines.csv", hashTableSquared, hashTableMultiplied, false);
                buscarPorClave(hashTableSquared, clave);
            } else if (opcion == 3) {
                break;
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    public static void leerCSVYGuardarEnHashTable(String filePath, HashTable<String, String[]> hashTableSquared, HashTable<String, String[]> hashTableMultiplied, boolean imprimir) {
        String line;
        String splitBy = ",";
        int id = 1;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] business = line.split(splitBy);
                if (imprimir) {
                    System.out.println("[" + id + "] Business [ID=" + business[0] + ", Name=" + business[1] + ", Address=" + business[2] + ", City=" + business[3] + ", State= " + business[4] + "]");
                }
                id++;
                String key = business[0];

                long startTimeSquared = System.nanoTime();
                hashTableSquared.put(key, business, true);
                long endTimeSquared = System.nanoTime();

                long startTimeMultiplied = System.nanoTime();
                hashTableMultiplied.put(key, business, false);
                long endTimeMultiplied = System.nanoTime();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void imprimirTodosLosDatos(HashTable<String, String[]> hashTable) {
        for (int i = 0; i < hashTable.numBuckets(); i++) {
            for (HashNode<String, String[]> node : hashTable.getBucketArray()[i]) {
                System.out.println("Business [ID=" + node.getValue()[0] + ", Name=" + node.getValue()[1] + ", Address=" + node.getValue()[2] + ", City=" + node.getValue()[3] + ", State= " + node.getValue()[4] + "]");
            }
        }
    }

    public static void buscarPorClave(HashTable<String, String[]> hashTable, String clave) {
        String[] business = hashTable.get(clave);
        if (business != null) {
            System.out.println("clave encontrada: [ID=" + business[0] + ", Name=" + business[1] + ", Address=" + business[2] + ", City=" + business[3] + ", State= " + business[4] + "]");
        } else {
            System.out.println("No se encontró ningúna clave: " + clave);
        }
    }
}
