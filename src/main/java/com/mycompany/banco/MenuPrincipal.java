/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.banco;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;


/**
 *
 * @author AndreaOrjuela
 */

public class MenuPrincipal {
    
    //UUID ui = UUID.randomUUID();
    
    
    private HashMap<Integer, Persona> mapPersonas = new HashMap<>();
    Scanner entrada = new Scanner(System.in);

    public MenuPrincipal() {
        Archivos arch = new Archivos();
        mapPersonas = arch.getListasActuales();
    }
    boolean salir = false;
    int opcion;
    
    public void MenuP() {

        while (!salir) {

            System.out.println("---- BANCO ----");
            System.out.println("1. Agregar persona.");
            System.out.println("2. Agregar record.");
            System.out.println("3. Eliminar record.");
            System.out.println("4. Listar persona y record.");
            System.out.println("5. Salir");

            try {

                System.out.println("Escribe la opcion");
                opcion = entrada.nextInt();

                switch (opcion) {
                    case 1:
                        agregarPersona();
                        break;
                    case 2:
                        agregarRecord();
                        break;
                    case 3:
                        eliminarRecord();
                        break;
                    case 4:
                        listarPersonas();
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Opcion no existente");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                entrada.next();
            }
        }
    }
    
    private void agregarPersona() {

        int identPersona = 0;
        String nombreP;
        String apellidoP;
        String correoP;
        Archivos arch = new Archivos();
        boolean valida = true;

        while (valida) {
            System.out.println("--- AGREGAR PERSONA ---");
            System.out.println("Documento: ");
            try {
                
                identPersona = Integer.parseInt(entrada.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Documento inválido");
            }
        }
        do {
            System.out.println("Nombre: ");
            nombreP = entrada.next();
        } while (validacionLetras(nombreP));
        do {
            System.out.println("Apellido: ");
            apellidoP = entrada.next();
        } while (validacionLetras(nombreP));

        do {
            System.out.println("Correo: ");
            correoP = entrada.next();
        } while (validacionCorreo(correoP));

        Persona p = new Persona(identPersona, nombreP, apellidoP, correoP);
        mapPersonas.put(identPersona, p);
        arch.guardarRegistros(mapPersonas);
    }

    private void agregarRecord() {
        Scanner sc = new Scanner(System.in);
        int documento = 0;
        boolean valida = true;
        while (valida) {
            System.out.println("Documento de la persona: ");
            try {
                documento = Integer.parseInt(sc.next());
                valida = false;
            } catch (InputMismatchException e) {
                System.out.println("Numero inválido");
            }
        }
        if (mapPersonas.containsKey(documento)) {
            agregarRecordDoc(documento);
        } else {
            System.out.println("La persona no existe.");
        }
    }
    
    private void agregarRecordDoc(int documento) {
        
        int codigo = 0;
        double valor = 0;
        boolean estado = true; 
        boolean valida = true;
        String empresa = null;
        Archivos arch = new Archivos();
        
        while (valida) {
            System.out.println("Codigo del record: ");
            try {
                codigo = Integer.parseInt(entrada.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.out.println("Numero inválido");
            }
        }

        estado = boleano();

        valida = true;
        while (valida) {
            System.out.println("Valor: ");
            try {
                valor = Double.parseDouble(entrada.next());
                valida = false;
            } catch (NumberFormatException e) {
                System.err.println("Numero inválido");
            }
        }

        Records record = new Records(codigo, empresa, estado, valor);
        mapPersonas.get(documento).getListaReportes().put(codigo, record);
        arch.guardarRegistros(mapPersonas);
    }

    private void eliminarRecord() {

        Archivos arch = new Archivos();
        int idPer = 0;
        int idRec = 0;
        boolean valida = true;

        while (valida) {
            System.out.println("Identificacion del usuario:");
            try {
                // la entrada se parsea y se le asigna al usario;
                idPer = Integer.parseInt(entrada.next());
                valida = false;
            } catch (InputMismatchException e) {
                System.err.println("Numero inválido");
            }
        }
        
        if (mapPersonas.containsKey(idPer)) {
            valida = true;
            while (valida) {
                System.out.println("Codigo del record:");
                try {
                    idRec = Integer.parseInt(entrada.next());
                    valida = false;
                } catch (NumberFormatException e) {
                    System.err.println("Numero inválido");
                }
            }
            /**
             * Si el mapa tiene la key que se ingreso en la identificacion de la persona
             * se recorre record y se trae la lista de reporte con el id del record
             */
            
            if (mapPersonas.get(idPer).getListaReportes().containsKey(idRec)) {
                Records reporte = mapPersonas.get(idPer).getListaReportes().get(idRec);
                if (!reporte.isEstado()) {
                    /**
                     * se busca en el mapa la key de id de la persona ingresado
                     * se traen los reportes por a key del id del record
                     */
                    mapPersonas.get(idPer).getListaReportes().remove(idRec);
                    arch.guardarRegistros(mapPersonas);
                    System.out.println("El record ha sido eliminado");
                } else {
                    System.out.println("No se puede eliminar el record");
                }
            } else {
                System.out.println("El record no existe.");
            }
        } 
    }

    private void listarPersonas() {
        
        Archivos arch = new Archivos();
        
        mapPersonas = arch.getListasActuales();
        mapPersonas.keySet().stream().map((p) -> {
            
            System.out.println("--- Lista de Personas ---");
            System.out.println("Identificacion: " + mapPersonas.get(p).getIdentPersona());
            System.out.println("Nombre: " + " " + mapPersonas.get(p).getNombre());        
            System.out.println("Apellido: " + mapPersonas.get(p).getApellido());
            System.out.println("Correo: " + mapPersonas.get(p).getCorreo());
                    
            return p;
            
        }).map((p) -> mapPersonas.get(p).getListaReportes()).forEachOrdered((tempRe) -> {
            
            if (tempRe.size() > 0) {
                tempRe.keySet().forEach((key) -> {
                    
                    System.out.println("--- RECORD ---");
                    System.out.println("Codigo del record: " + tempRe.get(key).getCodigoEmp());
                    System.out.println("Estado: " + tempRe.get(key).isEstado());
                    System.out.println("Valor :" + tempRe.get(key).getValor());
                });
            } else {
                System.out.println("Esta persona no tiene records");
            }
        });
    }

    

    
    
    private boolean validacionLetras(String palabra) {
        return palabra.contains("1") || palabra.contains("2") || palabra.contains("3") || palabra.contains("4") || palabra.contains("5")
                || palabra.contains("6") || palabra.contains("7") || palabra.contains("8") || palabra.contains("9") || palabra.contains("0");
    }

    private boolean validacionCorreo(String correo) {
        return !(correo.contains("@") && correo.contains(".com"));
    }

    private boolean boleano() {
        while (true) {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Estado: ");
            String estadoAux = entrada.next();
            switch (estadoAux) {
                case "true":
                    return true;
                case "false":
                    return false;
                default:
                    System.out.println("Por favor escriba solo true o false.");
                    break;
            }
        }
    }

}
