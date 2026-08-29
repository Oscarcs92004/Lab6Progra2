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

public class ComandoCd implements Comando{
    
    private SistemaArchivos sistema;
    
    public ComandoCd(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos){
        if (argumentos.length != 1){
            return "Uso correcto: Cd <nombre carpeta>";
        }
        
        String nombre = argumentos[0];
        File carpeta = sistema.buscar(nombre);
        
        if(!carpeta.exists()){
            return "Carpeta '"+nombre+"'no encontrada";
        }
        if(!carpeta.isDirectory()){
            return "La ruta indicada '"+nombre+"'no es de una carpeta.";
        }
        
        if(!sistema.cambiarDir(nombre)){
            return "No se pudo cambiar a la carpeta '"+nombre+"'";
        }
        
        return "Cambio a '"+nombre+"' exitoso.";
    }
    
}
