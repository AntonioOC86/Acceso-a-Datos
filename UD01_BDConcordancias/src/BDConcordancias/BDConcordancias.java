package BDConcordancias;

import java.io.*;
import java.util.ArrayList;
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

                        // Comprueba que no haya dejado al usuario sin aficiones
                        if (aficiones.trim().isEmpty()) {
                            System.out.println("Error: No se puede añadir un usuario sin aficiones.");
                        } else {

                            try (FileWriter fileWriter = new FileWriter(ruta, true)) {
                                fileWriter.write(codigoUsuario + " " + aficiones + "\n");
                                System.out.println("¡Usuario añadido correctamente!");
                            } catch (IOException e) {
                                System.out.println("Error al intentar guardar el usuario.");
                            }
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

                case 3 -> {
                    int minimoAficiones;

                    do {
                        System.out.println("¿Cuantás aficiones en comúnes en común buscas? (Mínimo 1): ");
                        minimoAficiones = entrada.nextInt();
                        entrada.nextLine();

                        if (minimoAficiones < 1) {
                            System.out.println("Las aficiones en común deben ser mayor o igual a 1.\n");
                        }
                    } while (minimoAficiones < 1);

                    ArrayList<String> usuariosLista = new ArrayList<>();

                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
                        String linea;
                        while ((linea = bufferedReader.readLine()) != null) {

                            if (!linea.trim().isEmpty()) {
                                usuariosLista.add(linea);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error al leer el archivo");
                    }

                    boolean hayCoincidencias = false;
                    int totalParejas = 0;

                    // Creamos la lista temporal para guardar y ordenar los resultados
                    ArrayList<Pareja> listaResultados = new ArrayList<>();

                    // Recorro el arrayList y compruebo a los usuarios entre sí
                    for (int usuarioAnt = 0; usuarioAnt < usuariosLista.size() - 1; usuarioAnt++) {
                        for (int usuarioPost = usuarioAnt + 1; usuarioPost < usuariosLista.size(); usuarioPost++) {

                            String lineaAnt = usuariosLista.get(usuarioAnt);
                            String lineaPost = usuariosLista.get(usuarioPost);

                            String[] datosAnt = lineaAnt.split(" ");
                            String[] datosPost = lineaPost.split(" ");

                            int coincidencias = 0;
                            StringBuilder palabrasComunes = new StringBuilder();

                            // Recorro aficiones de ambos
                            for (int aficionAnt = 1; aficionAnt < datosAnt.length; aficionAnt++) {
                                for (int aficionPost = 1; aficionPost < datosPost.length; aficionPost++) {

                                    if (datosAnt[aficionAnt].equals(datosPost[aficionPost])) {
                                        coincidencias++;
                                        palabrasComunes.append(datosAnt[aficionAnt]).append(" ");
                                    }
                                }
                            }

                            // Si superan el mínimo, los guardamos en la memoria temporal
                            if (coincidencias >= minimoAficiones) {
                                hayCoincidencias = true;
                                totalParejas++;

                                String resultado = (datosAnt[0] + " " + datosPost[0] + " " + palabrasComunes.toString().trim());
                                listaResultados.add(new Pareja(resultado, coincidencias));
                            }
                        }
                    }

                    // Fuera de todos los bucles evaluamos qué pasó
                    if (!hayCoincidencias) {
                        System.out.println("No se encontraron coincidencias con el número indicado de aficiones");
                    } else {

                        // 1. ORDENAMOS LA LISTA (de mayor a menor número de coincidencias)
                        listaResultados.sort((p1, p2) -> Integer.compare(p2.coincidencias, p1.coincidencias));

                        // 2. ESCRIBIMOS EL FICHERO DE GOLPE
                        try (FileWriter fileWriter = new FileWriter("UD01_BDConcordancias/datos/concordancias.txt")) {

                            System.out.println("\nResultados encontrados:");
                            for (Pareja p : listaResultados) {
                                System.out.println(p.texto); // Mostrar en consola
                                fileWriter.write(p.texto + "\n"); // Escribir en el .txt
                            }

                            System.out.println("\nFichero de concordancia generado correctamente en la carpeta datos," +
                                    " sobre el fichero: " + nombreArchivo + ". Contiene " + totalParejas + " parejas.");

                        } catch (IOException e) {
                            System.out.println("Error al crear el fichero de concordancias.");
                        }
                    }
                }
            }

        } while (opcion != 4);
    }

    static class Pareja {
        String texto;
        int coincidencias;

        public Pareja(String texto, int coincidencias) {
            this.texto = texto;
            this.coincidencias = coincidencias;
        }
    }
}
