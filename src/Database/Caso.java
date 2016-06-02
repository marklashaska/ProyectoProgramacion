/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 * Clase Caso
 *
 * @author Marcos Pineda Burriel
 * @version 21/05/2016
 */
public class Caso {

    private String id;
    private String nombre;
    private String desc;
    private String valor;
    private String juez;

    /**
     * Constructor de la clase caso, todos los atributos son obligatorios.
     *
     * @param id El id del caso.
     * @param nombre El nombre del caso.
     * @param desc Una breve descripción del caso.
     * @param valor Una estimación del valor total de dinero desviado.
     * @param juez El juez que investiga el caso.
     */
    public Caso(String id, String nombre, String desc, String valor, String juez) {
        this.id = id;
        this.nombre = nombre;
        this.desc = desc;
        this.valor = valor;
        this.juez = juez;
    }

    /**
     * Metodo de la clase caso que duvuelve el id del caso.
     *
     * @return El id del caso.
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo de la clase caso para cambiar el id del caso.
     *
     * @param id El id del caso.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo de la clase caso que devuelve el nombre del caso.
     *
     * @return el nombre del caso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo de la clase caso para cambiar el nombre del caso de corrupción.
     *
     * @param nombre El nombre del caso.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo de la clase caso que devuelve una breve descripción del caso.
     *
     * @return la descripción del caso.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Metodo de la clase caso para cambiar la descripción del caso.
     *
     * @param desc La descripción del caso.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Metodo de la clase caso que devuelve el dinero desviado.
     *
     * @return El valor total de dinero desviado.
     */
    public String getValor() {
        return valor;
    }

    /**
     * Metodo de la clase caso para cambiar el valor del dinero desviado.
     *
     * @param valor El valor total aproximado del dinero desviado.
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Metodo de la clase caso que devuelve el juez del caso.
     *
     * @return El juez que se encarga del caso.
     */
    public String getJuez() {
        return juez;
    }

    /**
     * Metodo de la clase caso para cambiar el juez del caso.
     *
     * @param juez El juez que se encarga del caso.
     */
    public void setJuez(String juez) {
        this.juez = juez;
    }

}
