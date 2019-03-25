/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import java.io.Serializable;

/**
 *
 * @author AndreaOrjuela
 */
public class Records implements Serializable{
    
    private int codigoEmp;
    private String nombreEmp;
    private boolean estado;
    private double valor;

    public Records(int codigoEmp, String nombreEmp, boolean estado, double valor) {
        this.codigoEmp = codigoEmp;
        this.nombreEmp = nombreEmp;
        this.estado = estado;
        this.valor = valor;
    }
    
    /**
     * 
     * Getter y Setters. 
     */

    public int getCodigoEmp() {
        return codigoEmp;
    }

    public void setCodigoEmp(int codigoEmp) {
        this.codigoEmp = codigoEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    
    
}
