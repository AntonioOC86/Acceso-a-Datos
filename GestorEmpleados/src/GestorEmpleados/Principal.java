package GestorEmpleados;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {

        GestionEmpleados gestor = new GestionEmpleados();

        Empleado emp1 = new Empleado("12345679c", "Juan", "Pérez", 5551234f, 3000.0);
        Empleado emp2 = new Empleado("12345678b", "Ana", "García", 5555678f, 2500.0);
        Empleado emp3 = new Empleado("12345678a", "Pedro", "López", 5558765f, 2800.0);

        gestor.agregarEmpleado(emp1);
        gestor.agregarEmpleado(emp2);
        gestor.agregarEmpleado(emp3);

        Scanner entrada = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n --- MENÚ DE OPCIONES ---");
            System.out.println("1.Ordenar por DNI");
            System.out.println("2.Ordenar por Nombre");
            System.out.println("3.Ordenar por Apellido");
            System.out.println("4.Ordenar por Salario");
            System.out.println("5.Ordenar por Teléfono");
            System.out.println("6.Salir");
            System.out.println("Elige una opción: ");

            opcion = entrada.nextInt();
            System.out.println();

            switch (opcion) {
                case 1 -> {
                    gestor.ordenarPorDni();
                    System.out.println("Empleados ordenados por DNI:");
                    gestor.mostrarEmpleados();
                }
                case 2 -> {
                    gestor.ordenarPorNombre();
                    System.out.println("Empleados ordenados por nombre:");
                    gestor.mostrarEmpleados();
                }
                case 3 -> {
                    gestor.ordenarPorApellido();
                    System.out.println("Empleados ordenados por apellido:");
                    gestor.mostrarEmpleados();
                }
                case 4 -> {
                    gestor.ordenarPorSalario();
                    System.out.println("Empleados ordenados por salario:");
                    gestor.mostrarEmpleados();
                }
                case 5 -> {
                    gestor.ordenarPorTelefono();
                    System.out.println("Empleados ordenados por teléfono:");
                    gestor.mostrarEmpleados();
                }
                case 6 -> {
                    System.out.println("¡Gracias por usar el sistema! Saliendo...");
                    Thread.sleep(3000);
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }

        } while (opcion != 6);



        /* Parte del ejercicio 1 */

        // System.out.println("Empleados antes de ordenar:");
        // gestor.mostrarEmpleados();
        //
        // System.out.println("---------------------------------------------------\n");
        //
        // gestor.ordenarPorDni();
        //
        // System.out.println("Empleados ordenados por DNI:");
        // gestor.mostrarEmpleados();

//      /* -----------------------------------------------------*/


    }
}
