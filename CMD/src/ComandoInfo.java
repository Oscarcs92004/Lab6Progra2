/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author oscar
 */
public class ComandoInfo implements Comando{
    private SistemaArchivos sistema;

    public ComandoInfo(SistemaArchivos sistema) {
        this.sistema = sistema;
    }

    @Override
    public String ejecutar(String[] args) {
        if (args.length != 1) {
            return "Uso correcto: Info <nombre>";
        }
        File archivo = sistema.buscar(args[0]);
        if (!archivo.exists()) {
            return "Error: \"" + args[0] + "\" no existe.";
        }
        String tipo;
        if (archivo.isDirectory()) {
            tipo = "Carpeta";
        } else {
            tipo = "Archivo";
        }
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fecha = formato.format(new Date(archivo.lastModified()));
        return "Tipo: " + tipo+ "\nRuta: " + archivo.getAbsolutePath()+ "\nTamaño: " + archivo.length() + " bytes"+ "\nÚltima modificación: " + fecha;
    }
}
