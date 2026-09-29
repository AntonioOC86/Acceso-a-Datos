package BDConcordancias;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BDConcordancias {
    static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        String ruta = "";
        String nombreArchivo = ""; // Guardamos el nombre para usarlo luego en la opción 3
        boolean ficheroValido = false;

        System.out.println(" --- GESTOR DE AFICIONES ---");


        do {
            System.out.println("¿Sobre qué fichero quieres trabajar? (ej: usuarios.txt): ");
            nombreArchivo = entrada.nextLine();

            ruta = "UD01_BDConcordancias/datos/" + nombreArchivo;
            File fichero = new File(ruta);

            if (fichero.exists()) {
                // Si existe, comprobamos que no supere los 10000 bytes
                if (fichero.length() > 10000) {
                    System.out.println("Error: El fichero es demasiado grande (supera los 10000 bytes).");
                } else {
                    System.out.println("Fichero cargado correctamente.");

                    // Aviso al usuario
                    long tamanyoActual = fichero.length();
                    if (tamanyoActual >= 9000) {
                        System.out.println("\n⚠ ¡AVISO IMPORTANTE!");
                        System.out.println("El fichero ocupa " + tamanyoActual + " bytes de los 10000 permitidos.");
                        System.out.println("Si sigue añadiendo usuarios y supera el límite, el fichero se bloqueará" +
                                "por seguridad la próxima vez que inicie el programa.\n");
                    }
                    ficheroValido = true;
                }
            } else {
                try {
                    if (fichero.createNewFile()) {
                        System.out.println("El fichero no existía. Se ha creado uno nuevo en " +
                                "blanco llamado " + nombreArchivo);
                    }
                    ficheroValido = true;

                } catch (IOException e) {
                    System.out.println("Error: No se ha podido crear el fichero. Revisa que el nombre sea válido.");
                }
            }

        } while (!ficheroValido);

        int opcion;
        do {
            System.out.println("\n ===== MENÚ PRINCIPAL ===== \n");
            System.out.println("1. Añadir usuario");
            System.out.println("2. Mostrar usuarios introducidos");
            System.out.println("3. Generar fichero de concordancias");
            System.out.println("4. Salir\n");
            System.out.println("=============================\n");
            System.out.println("Seleccione una opción:");

            try {
                opcion = entrada.nextInt();
                entrada.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Error: Tienes que introducir números.");
                entrada.nextLine();
                opcion = -1;
            }

            System.out.println();

            switch (opcion) {
                case 1 -> {
                    int maxNumero = 99; // Se empieza en 99 por si el archivo está vacío

                    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = br.readLine()) != null) {
                            String codigoTexto = linea.split(" ")[0];
                            String numeroTexto = codigoTexto.substring(1);
                            int numero = Integer.parseInt(numeroTexto);

                            maxNumero = Math.max(maxNumero, numero);
                        }
                    } catch (Exception e) {
                        System.out.println("Aviso interno: Reiniciando contador de sugerencias.");
                    }

                    String codigoSugerido = "U" + (maxNumero + 1);

                    System.out.println("Escribe el código del nuevo usuario (Sugerencia: " + codigoSugerido + "):");
                    String codigoUsuario = entrada.nextLine().toUpperCase();

                    boolean existe = false;

                    try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = br.readLine()) != null)  {

                            if (linea.startsWith(codigoUsuario + " ")) {
                                existe = true;
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error al leer el fichero.");
                    }

                    if (existe) {
                        System.out.println("Error: El código de usuario '" + codigoUsuario + "' ya existe en el fichero.");
                    } else {
                        System.out.println("Escribe sus aficiones separadas por espacios (ej: ARTE GOLF FÚTBOL):");
                        String aficiones = entrada.nextLine().toUpperCase();

                        try (FileWriter fileWriter = new FileWriter(ruta, true)) {
                            fileWriter.write(codigoUsuario + " " + aficiones + "\n");
                            System.out.println("¡Usuario añadido correctamente!");
                        } catch (IOException e) {
                            System.out.println("Error al intentar guardar el usuario.");
                        }
                    }
                }

                case 2 -> {
                    System.out.println("-- Listado de usuarios --");

                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = bufferedReader.readLine()) != null) {
                            System.out.println(linea);
                        }

                    } catch (FileNotFoundException e) {
                        System.out.println("Aviso: No hay nada que mostrar. El fichero está vacío." +
                                "no existe aún.");
                    } catch (IOException e) {
                        System.out.println("Error inesperado al intentar leer el fichero.");
                    }
                }
            }

        } while (opcion != 4);
    }
}
