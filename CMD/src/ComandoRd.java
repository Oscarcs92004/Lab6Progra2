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
import java.io.FileReader;
import java.io.IOException;

public class ComandoRd implements Comando{
    
    private SistemaArchivos sistema;
    
    public ComandoRd(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos){
        if (argumentos.length != 1){
            return "Uso correcto: Rd <archivo.ext>";
        }
        
        
        String nombre = argumentos[0];
        File archivo = sistema.buscar(nombre);
        
        if (!archivo.exists()){
            return "No se pudo encontrar el archivo '"+nombre+"'.";
        }
        if (!archivo.isFile()){
            return "'"+nombre+"' no es un archivo.";
        }
        
        String contenido = "";
        
        try(FileReader lector = new FileReader(archivo)){
            int car;
            while ((car = lector.read()) != -1){
                contenido += (char) car;
            }
        } catch(IOException e){
            return "Error leyendo el archivo '"+nombre+"': "+e.getMessage();
        }
        
        if(contenido.isEmpty()){
            return "(No tiene contenido)";
        }
        
        return contenido;
    }
}
