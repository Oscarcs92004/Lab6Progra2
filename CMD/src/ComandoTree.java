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

public class ComandoTree implements Comando{
    
    private SistemaArchivos sistema;

    public ComandoTree(SistemaArchivos sistema) {
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String[] argumentos) {
        File actual = sistema.getActual();
        String salida = (actual.getName().isEmpty() ? "." : actual.getName()) + "\n";
        salida = construirArbol(actual, "", salida);

        return salida.trim();
    }

    private String construirArbol(File carpeta, String Anterior, String salida) {
        File[] lista = carpeta.listFiles();
        if (lista == null) {
            return salida;
        }

        for (int i = 0; i < lista.length; i++) {
            File f = lista[i];
            boolean esUltimo = false;
            if (i == lista.length - 1){
                esUltimo = true;
            }

            salida += Anterior + (esUltimo ? "\\---" : "+---") + f.getName() + "\n";

            if (f.isDirectory()) {
                String nuevoAnterior = Anterior + (esUltimo ? "    " : "|   ");
                salida = construirArbol(f, nuevoAnterior, salida);
            }
        }

        return salida;
    }
}
