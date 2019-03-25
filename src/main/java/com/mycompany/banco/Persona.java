/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author AndreaOrjuela
 */
public class Persona implements Serializable{
    
    private int identPersona;
    private String nombre;
    private String apellido;
    private String correo;
    private HashMap<Integer, Records> listaReportes;

    public Persona(int identPersona, String nombre, String apellido, String correo) {
        this.identPersona = identPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        listaReportes = new HashMap<>();
    }

    /**
     * 
     * Getters y Setters
     */
    
    public int getIdentPersona() {
        return identPersona;
    }

    public void setIdentPersona(int identPersona) {
        this.identPersona = identPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public HashMap<Integer, Records> getListaReportes() {
        return listaReportes;
    }

    public void setListaReportes(HashMap<Integer, Records> listaReportes) {
        this.listaReportes = listaReportes;
    }

    

    
}
