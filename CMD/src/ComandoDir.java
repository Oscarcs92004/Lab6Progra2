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

public class ComandoDir implements Comando {
    
    private SistemaArchivos sistema;
    
    public ComandoDir(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String argumentos[]){
        File actual = sistema.getActual();
        File[] lista = actual.listFiles();
        
        if (lista == null || lista.length == 0){
            return "La carpeta esta vacia";
        }
        
        String resultado = "Directorio de '"+sistema.getRutaActual()+"': \n\n";
        
        int contDir = 0, contFile = 0;
        
        for(File f: lista){
            if(f.isDirectory()){
                resultado += "<DIR>     "+f.getName()+"\n";
                contDir++;
            } else{
                resultado += "          "+f.getName()+"\n";
                contFile++;
            }
        }
        
        resultado += "\n     "+contFile+" archivo(s)";
        resultado += "\n     "+contDir+" carpeta(s)";
        
        return resultado;
    }
    
}
