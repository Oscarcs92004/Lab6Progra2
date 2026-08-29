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
public class ComandoRm implements Comando {
    private SistemaArchivos sistema;

    public ComandoRm(SistemaArchivos sistema) {
        this.sistema = sistema;
    }

    @Override
    public String ejecutar(String[] args) {

        if (args.length != 1) {
            return "Uso correcto: Rm <nombre>";
        }

        String nombre = args[0];

        File archivo = sistema.buscar(nombre);

        if (!archivo.exists()) {
            return "Error: \"" + nombre + "\" no existe.";
        }

        if (sistema.eliminar(archivo)) {
            return "\"" + nombre + "\" eliminado correctamente.";
        }

        return "Error: No se pudo eliminar \"" + nombre + "\".";
    }
}
