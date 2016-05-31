/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author Marcos Pineda
 */
public final class Ciudadano {

    private String id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String patrimonio;
    private String caso;

    /**
     * Constructor de la clase Ciudadano, todos sus atributos son obligatorios.
     *
     * @param id ID del ciudadano.
     * @param dni DNI del ciudadano.
     * @param nombre nombre del ciudadano.
     * @param apellidos apellidos del ciudadano.
     * @param direccion direccion del ciudadano.
     * @param patrimonio patrimonio en euros que posee el ciudadano.
     * @param caso id del caso de corrupción en el que esta implicado el
     * ciudadano.
     */
    public Ciudadano(String id, String dni, String nombre, String apellidos, String direccion, String patrimonio, String caso) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.patrimonio = patrimonio;
        this.caso = caso;
    }

    /**
     * @return la id del ciudadano
     */
    public String getId() {
        return id;
    }

    /**
     * @param id La id del ciudadano
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return el DNI del ciudadano
     */
    public String getDni() {
        return dni;
    }

    /**
     * @param dni El DNI del ciudadano
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * @return el nombre del ciudadano
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre del ciudadano
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return los apellidos del ciudadano.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * @param apellidos los apellidos del ciudadano.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * @return la direccion del ciudadano.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion la direccion del ciudadano
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return el patrimonio en euros del ciudadano
     */
    public String getPatrimonio() {
        return patrimonio;
    }

    /**
     * @param patrimonio el patrimonio en euros del ciudadano
     */
    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    /**
     * @return el caso de corrupción en el que esta implicado
     */
    public String getCaso() {
        return caso;
    }

    /**
     * @param caso el caso de corrupción en el que esta implicado
     */
    public void setCaso(String caso) {
        this.caso = caso;
    }
}
