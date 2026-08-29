/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author oscar
 */
public class ComandoCopy implements Comando{
     private SistemaArchivos sistema;

    public ComandoCopy(SistemaArchivos sistema) {
        this.sistema = sistema;
    }

    @Override
    public String ejecutar(String[] args) {
        if (args.length != 2) {
            return "Uso correcto: Copy <origen> <destino>";
        }
        File origen = sistema.buscar(args[0]);
        File destino = sistema.buscar(args[1]);
        if (!origen.exists()) {
            return "Error: El archivo de origen no existe.";
        }
        if (origen.isDirectory()) {
            return "Error: El origen debe ser un archivo.";
        }
        if (destino.exists()) {
            return "Error: El archivo de destino ya existe.";
        }
        try {
            FileReader lector = new FileReader(origen);
            FileWriter escritor = new FileWriter(destino);
            int caracter;
            while ((caracter = lector.read()) != -1) {
                escritor.write(caracter);
            }
            lector.close();
            escritor.close();
            return "Archivo copiado correctamente.";
        } catch (IOException e) {
            return "Error al copiar el archivo: " + e.getMessage();
        }
    }
}
