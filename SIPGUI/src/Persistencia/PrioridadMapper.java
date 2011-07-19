/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Negocio.Modelo.Prioridad;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 *
 * @author NicolasM
 */
public class PrioridadMapper {
    // <editor-fold>

    private static PrioridadMapper instancia = new PrioridadMapper();

    public static PrioridadMapper getInstancia() {
        return instancia;
    }
    // </editor-fold>
    private HashMap<Integer, Prioridad> prioridades = new HashMap<Integer, Prioridad>();

    private PrioridadMapper() {
        registrarPrioridad(new Prioridad(0, "Sin asignar", -1, -1));
        //registrarPrioridad(new Prioridad(0, "En una quincena", Calendar.DAY_OF_MONTH, 15));
        registrarPrioridad(new Prioridad(1, "En 1 mes", Calendar.MONTH, 1));
        registrarPrioridad(new Prioridad(2, "En 2 meses", Calendar.MONTH, 2));
        registrarPrioridad(new Prioridad(3, "En 3 meses", Calendar.MONTH, 3));
        registrarPrioridad(new Prioridad(4, "En 4 meses", Calendar.MONTH, 4));
        registrarPrioridad(new Prioridad(5, "En 5 meses", Calendar.MONTH, 5));
        registrarPrioridad(new Prioridad(6, "En 6 meses", Calendar.MONTH, 6));
        registrarPrioridad(new Prioridad(7, "En 7 meses", Calendar.MONTH, 8));
        registrarPrioridad(new Prioridad(8, "En 8 meses", Calendar.MONTH, 9));
        registrarPrioridad(new Prioridad(9, "En 9 meses", Calendar.MONTH, 10));
        registrarPrioridad(new Prioridad(10, "En 10 meses", Calendar.MONTH, 11));
        registrarPrioridad(new Prioridad(11, "En 11 meses", Calendar.MONTH, 12));
        registrarPrioridad(new Prioridad(12, "En un año", Calendar.YEAR, 1));

    }

    private void registrarPrioridad(Prioridad prioridad) {
        prioridades.put(prioridad.getNivel(), prioridad);
    }

    public ArrayList<Prioridad> CargarTodos() {
        return new ArrayList<Prioridad>(prioridades.values());
    }
    //TODO: generar metodos de acceso a prioridades
}
