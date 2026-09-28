import java.io.*;

public class crearFichero {
    static void main(String[] args) {
        String ruta = "datos/otro.txt";

        //Creamos y escribimos en fichero
        try (FileWriter fileWriter = new FileWriter(ruta, true)) {
            fileWriter.write("helloooo\n");
            fileWriter.write("eeeeee\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //Leer contenido de fichero
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (FileNotFoundException e ) {
            throw new RuntimeException(e);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Borrar fichero
        /*File file = new File(ruta);
        if (file.delete()) {
            System.out.println("Fichero eliminado");
        }
        else {
            System.out.println("No se ha borrado");
        }*/
    }
}
