package Negocio.Modelo;

public class Cliente {
    //PrimaryKey

    private long cuit;
    private String nombre;
    private String direccion;
    private String codigoPostal;
    private String telefono;
    private String fax;
    private boolean habilitado = true;

    public String getNombre() {
        return nombre;
    }

    public long getCuit() {
        return cuit;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFax() {
        return fax;
    }

    public boolean getHabilitado() {
        return this.habilitado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCuit(long cuit) {
        this.cuit = cuit;
    }

    public void setDireccion(String calle) {
        this.direccion = calle;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean isEmpty() {

        return (this.codigoPostal.isEmpty() && this.cuit == 0
                && this.direccion.isEmpty() && this.fax.isEmpty() && this.nombre.isEmpty()
                && this.telefono.isEmpty()) ? true : false;
    }
}
