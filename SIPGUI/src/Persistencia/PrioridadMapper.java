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
    public static PrioridadMapper getInstancia(){
        return instancia;
    }
    // </editor-fold>
    
    private HashMap<Integer, Prioridad> prioridades = new HashMap<Integer , Prioridad>();
    private PrioridadMapper(){
        registrarPrioridad(new Prioridad(1, "En una quincena", Calendar.DAY_OF_MONTH, 14));
        registrarPrioridad(new Prioridad(2, "En un mes", Calendar.MONTH, 1));
        registrarPrioridad(new Prioridad(3, "En un bimestre", Calendar.MONTH, 2));
        registrarPrioridad(new Prioridad(4, "En un semestre", Calendar.MONTH, 6));
        registrarPrioridad(new Prioridad(5, "En un año", Calendar.YEAR, 1));
    }
    
    private void registrarPrioridad(Prioridad prioridad){
        prioridades.put(prioridad.getNivel(), prioridad);
    }
    
    public ArrayList<Prioridad> CargarTodos(){
        return new ArrayList<Prioridad>(prioridades.values());
    }
    
    //TODO: generar metodos de acceso a prioridades
}
