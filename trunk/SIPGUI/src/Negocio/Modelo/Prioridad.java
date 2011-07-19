/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio.Modelo;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author NicolasM
 */
public class Prioridad {
    private int nivel;
    private String descripcion;

    private int tiempoEspera;
    private int unidadEspera;

    public int getNivel(){
        return nivel;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    
    public Prioridad(int nivel, String descripcion, int unidadEspera, int tiempoEspera){
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.tiempoEspera = tiempoEspera;
        this.unidadEspera = unidadEspera;
    }
    
    
    public Date calcularFechaEsperadaDeEntrega(Date fechaPedido){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaPedido);
        
        calendar.add(unidadEspera, tiempoEspera);
        return calendar.getTime();
    }
}
