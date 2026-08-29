/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
/**
 *
 * @author oscar
 */
public class SistemaArchivos {
    private File raiz;
    private File actual;
    
    public SistemaArchivos(){
        raiz = new File("Consola");
        
        if(!raiz.exists()){
            raiz.mkdir();
        }
        
        actual = raiz;
    }
    
    public File getRaiz(){
        return raiz;
    }
    
    public File getActual(){
        return actual;
    }
    
    public String getRutaActual(){
        return actual.getAbsolutePath();
    }
    
    public File buscar(String nombre){
        return new File(actual,nombre);
    }
    
    public boolean crearDir(String nombre){
        File nuevoDir = new File(actual, nombre);
        if(nuevoDir.exists()){
            return false;
        }
        return nuevoDir.mkdir();
    }
    
    public boolean cambiarAnterior(){
        
    }
}
