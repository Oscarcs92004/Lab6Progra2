/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author oscar
 */
public class ComandoDate implements Comando {
    public ComandoDate(SistemaArchivos sistema) {
        // no es necesario agregarle nada al constructor no se utilizara el sistema de archivos en este comando
    }

    @Override
    public String ejecutar(String[] args) {

        if (args.length != 0) {
            return "Uso correcto: Date";
        }

        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return "Fecha actual: " + formato.format(fecha);
    }
}
