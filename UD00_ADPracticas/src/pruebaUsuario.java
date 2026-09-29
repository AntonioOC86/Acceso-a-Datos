import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class pruebaUsuario {
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String ruta = "UD00_ADPracticas/datos/inventario.txt";
        int opcion;
        do {

            //Hablar con el usuario por la CONSOLA
            System.out.println("\n--- MENÚ DE OPCIONES ---");
            System.out.println("1. Añadir producto (Escribir en fichero)");
            System.out.println("2. Quitar fichero completo (Borrar)");
            System.out.println("3. Mostrar productos (Leer fichero)");
            System.out.println("4. Salir");
            System.out.println("Elige una opción: ");

            try {
                opcion = entrada.nextInt();
                entrada.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Tienes que introducir números.");
                entrada.nextLine();
                opcion = -1;
            }

            System.out.println();  // Salto de línea estético


            switch (opcion) {
                case 1 -> {
                    System.out.println("Escribe el producto que quieres añadir: ");
                    String producto = entrada.nextLine();

                    try (FileWriter fileWriter = new FileWriter(ruta, true)) {
                        fileWriter.write(producto + "\n");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                case 2 -> {
                    File file = new File(ruta);
                    if (file.delete()) {
                        System.out.println("Fichero eliminado");
                    } else {
                        System.out.println("No se ha borrado el fichero o no existe aún.");
                    }
                }
                case 3 -> {
                    System.out.println("Contenido del inventario:");

                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = bufferedReader.readLine()) != null) {
                            System.out.println(linea);
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Aviso: No hay nada que mostrar. El inventario está vacío o el fichero " +
                                "no exite aún.");
                    } catch (IOException e) {
                        System.out.println("Error inesperado al intentar leer el fichero.");
                    }
                }
                case 4 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}
