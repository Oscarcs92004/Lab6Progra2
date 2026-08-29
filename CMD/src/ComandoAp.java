/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author oscar
 */
public class ComandoAp implements Comando{
    private SistemaArchivos sistema;

    public ComandoAp(SistemaArchivos sistema) {
        this.sistema = sistema;
    }
    
    @Override 
    public String ejecutar(String[] args){
        if (args.length != 1) {
            return "Uso correcto: Ap <archivo.ext>";
        }

        File archivo = sistema.buscar(args[0]);

        if (!archivo.exists()) {
            return "Error: El archivo \"" + args[0] + "\" no existe.";
        }

        if (archivo.isDirectory()) {
            return "Error: \"" + args[0] + "\" es una carpeta.";
        }

        return "MODO_APPEND";
    }
    
    public String agregar(File archivo, String contenido) {
        try {
            FileWriter escritor = new FileWriter(archivo, true);
            escritor.write(contenido);
            escritor.close();
            return "Contenido agregado correctamente.";
        } catch (IOException e) {
            return "Error al modificar el archivo: " + e.getMessage();
        }
    }
}
