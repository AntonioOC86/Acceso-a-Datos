package GestorEmpleados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionEmpleados {

    private final ArrayList<Empleado> listaEmpleados;

    public GestionEmpleados() {
        this.listaEmpleados = new ArrayList<>();
    }

    public void agregarEmpleado(Empleado empleado) {
        this.listaEmpleados.add(empleado);
    }

    public void ordenarPorDni() {
        Collections.sort(this.listaEmpleados);
    }

    public void ordenarPorNombre() {
        this.listaEmpleados.sort(Comparator.comparing(Empleado::getNombre));
    }

    public void ordenarPorApellido() {
        this.listaEmpleados.sort(Comparator.comparing(Empleado::getApellido));
    }

    public void ordenarPorSalario() {
        this.listaEmpleados.sort(Comparator.comparing(Empleado::getSalario));
    }

    public void ordenarPorTelefono() {
        this.listaEmpleados.sort(Comparator.comparing(Empleado::getTelefono));
    }

    public void mostrarEmpleados() {

        for (Empleado empleado : listaEmpleados) {
            System.out.println(empleado);
        }
    }
}
