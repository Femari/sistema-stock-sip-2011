/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Negocio.Modelo.Cliente;
import Persistencia.ClienteMapper;
import java.util.ArrayList;

/**
 *
 * @author Dan
 */
public class ProcesarClientes implements IProcesarClientes {
    
    private ArrayList<Cliente> clientes;
    private Cliente cliente;
    
    public ArrayList<Cliente> getClientes() {
        return clientes = ClienteMapper.getInstancia().CargarTodos();
    }

    public ArrayList<Cliente> getCliente(Cliente cliente) {
        return ClienteMapper.getInstancia().CargarTodos(cliente);
    }

    public void agregarCliente(Cliente cliente) {
        ClienteMapper.getInstancia().Agregar(cliente);
    }

    public void modificarCliente(Cliente ClienteModificado) {
        ClienteMapper.getInstancia().Modificar(ClienteModificado);
    }

    public boolean existeCliente(Cliente cliente) {
        ArrayList<Cliente> clients = ClienteMapper.getInstancia().CargarTodos(cliente);
        return clients.isEmpty()?false:true;
    }
    
    public void bajaCliente (Cliente cliente){
        cliente.setHabilitado(false);
        ClienteMapper.getInstancia().Modificar(cliente);
    }
   
    
}
