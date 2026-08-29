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
import java.io.IOException;

public class ComandoMfile implements Comando{
    
    private SistemaArchivos sistema;
    
    public ComandoMfile(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos){
        if (argumentos.length != 1){
            return "Uso correcto: Mfile <nombre.txt>";
        }
        
        String nombre = argumentos[0];
        
        File nuevoArchivo = sistema.buscar(nombre);
        
        if (nuevoArchivo.exists()){
            return "Error: Ya existe un archivo o directorio con el nombre: '"+nombre+"'";
        }
        
        try{
            if (nuevoArchivo.createNewFile()){
                return "Archivo '"+nombre+"' creado exitosamente";
            } else {
                return "No se pudo crear el archivo '"+nombre+"'";
            }
        } catch(IOException e){
            return "Error creando el archivo '"+nombre+"': "+e.getMessage();
        }
    }
}
