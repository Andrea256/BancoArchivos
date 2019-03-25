/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.banco;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author AndreaOrjuela
 */
public class Archivos {
    
    public HashMap<Integer, Persona> listaPersona;
    /**
     * Ruta del archivo plano
     */
    
    private final String ruta;
    
    public Archivos(){
        this.ruta = "record.txt";
        validarArchivos();
    }
    
    private void validarArchivos() {

        try {
            File file = new File(ruta);

            if (!file.exists()) {
                file.createNewFile();
                listaPersona = new HashMap<>();
                guardarRegistros(listaPersona);
            }
        } catch (IOException e) {
            System.err.println("----- Excepcion -----");
            System.err.println("Error con el archivo");
            e.getMessage();
        }

    }
    
    public HashMap<Integer, Persona> getListasActuales() {
        
        listaPersona = new HashMap<>();

        try {
            ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ruta));
            listaPersona = (HashMap<Integer, Persona>)entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("----- Excepcion -----");
            System.err.println("No hay datos en el archivo");
            e.getMessage();
        }

        return listaPersona;
    }
    
    public void guardarRegistros(HashMap<Integer, Persona> lista) {

        try {
            try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ruta))) {
                salida.writeObject(lista);
            }
        } catch (IOException e) {
            System.err.println("----- Excepcion -----");
            System.err.println("No se ha podido guardar el registro");
            e.getMessage();
        }
    }
    
}
