/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Negocio.Modelo.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public interface IProcesarClientes {

    public abstract ArrayList<Cliente> getClientes();

    public abstract ArrayList<Cliente> getCliente(Cliente cliente);

    public abstract void agregarCliente(Cliente cliente);

    public abstract void modificarCliente(Cliente ClienteActual, Cliente ClienteModificado);
    
    public abstract boolean existeCliente(Cliente cliente);
}