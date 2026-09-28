package BDConcordancias;

import java.io.FileWriter;
import java.util.Scanner;

public class BDConcordancias {
    static void main(String[] args) {


        Scanner entrada = new Scanner(System.in);
        String ruta = "src/BDConcordancias/usuarios.txt";
        int opcion;

        do {
            System.out.println("\n ===== MENÚ PRINCIPAL ===== \n");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Mostrar usuarios introducidos");
            System.out.println("3. Generar fichero de concordancias");
            System.out.println("4. Salir\n");
            System.out.println("=============================\n");
            System.out.println("Seleccione una opción:");



            opcion = entrada.nextInt();
            entrada.nextLine();

            System.out.println();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Escribe el nombre de usuario a añadir:");
                    String usuario = entrada.nextLine();

                    try (FileWriter fileWriter = new FileWriter(ruta, true)) {
                        fileWriter.write(usuario + "\n");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        } while (opcion != 4);
    }
}
