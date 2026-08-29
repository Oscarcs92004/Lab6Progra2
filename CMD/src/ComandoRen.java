/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */

import java.io.File;

public class ComandoRen implements Comando{
    
    private SistemaArchivos sistema;
    
    public ComandoRen(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos){
        if (argumentos.length != 2){
            return "Uso correcto: Ren <actual> <nuevo>";
        }
        
        String nombreActual = argumentos[0];
        String nombreNuevo = argumentos[1];
        
        File original = sistema.buscar(nombreActual);
        File nuevo = sistema.buscar(nombreNuevo);
        
        if (!original.exists()){
            return "No se pudo encontrar el archivo/carpeta '"+nombreActual+"'.";
        }
        if (nuevo.exists()){
            return "Ya existe un archivo/carpeta con el nombre '"+nombreActual+"'.";
        }
        
        if (original.renameTo(nuevo)){
            return "'"+nombreActual+"' renombrado a '"+nombreNuevo+"'.";
        } else {
            return "'"+nombreActual+"' No se pudo renombrar.";
        }
    }
}
