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
import java.util.ArrayList;


public class ComandoFind implements Comando {
    
    private SistemaArchivos sistema;
    
    public ComandoFind(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos){
        if(argumentos.length != 1){
            return "Uso correcto: Find <nombre>";
        }
        
        String buscado = argumentos[0];
        ArrayList<String> resultados = new ArrayList<>();
        buscarRec(sistema.getActual(), buscado, resultados);
        
        if (resultados.isEmpty()){
            return "No se encontraron archivos/carpetas que contengan '"+buscado+"'";
        }
        
        String resultadoFinal = "Resultados encontrados de '"+buscado+"': \n";
        
        for(String ruta:resultados){
            resultadoFinal += ruta + "\n";
        }
        
        return resultadoFinal;
    }
    
    private void buscarRec(File carpeta, String texto, ArrayList<String> resultados){
        File[] listaActual = carpeta.listFiles();
        if (listaActual == null){
            return;
        }
        for (File f: listaActual){
            if(f.getName().toLowerCase().contains(texto.toLowerCase())){
                resultados.add(f.getAbsolutePath());
            }
            if (f.isDirectory()){
                buscarRec(f,texto,resultados);
            }
        }
    }
}
