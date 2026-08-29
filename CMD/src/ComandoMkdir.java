/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */
public class ComandoMkdir implements Comando{
    private SistemaArchivos sistema;
    
    public ComandoMkdir(SistemaArchivos sistema){
        this.sistema = sistema;
    }
    
    @Override
    public String ejecutar(String args[]){
        if (args.length != 1) {
            return "Uso correcto: Mkdir <nombre>";
        }

        String nombre = args[0];

        if (nombre.trim().isEmpty()) {
            return "Error: Debe especificar el nombre de la carpeta.";
        }

        if (sistema.crearDir(nombre)) {
            return "Carpeta \"" + nombre + "\" creada correctamente.";
        }

        return "Error: No se pudo crear la carpeta \"" + nombre + "\". Puede que ya exista.";
    }
}
