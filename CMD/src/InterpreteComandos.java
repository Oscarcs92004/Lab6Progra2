/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.HashMap;
/**
 *
 * @author oscar
 */
public class InterpreteComandos {
    private HashMap<String, Comando> comandos;
    private SistemaArchivos sistema;

    public InterpreteComandos(SistemaArchivos sistema) {
        this.sistema = sistema;
        comandos = new HashMap<>();
        comandos.put("Mkdir",new ComandoMkdir(sistema));
        comandos.put("Rm",new ComandoRm(sistema));
        comandos.put("..",new ComandoAnterior(sistema));
        comandos.put("Date",new ComandoDate(sistema));
        comandos.put("Wr",new ComandoWr(sistema));
        comandos.put("Ap",new ComandoAp(sistema));
        comandos.put("Copy",new ComandoCopy(sistema));
        comandos.put("Info",new ComandoInfo(sistema));
        comandos.put("Cls",new ComandoCls(sistema));
        comandos.put("Exit",new ComandoExit(sistema));
        comandos.put("Mfile", new ComandoMfile(sistema));
        comandos.put("Cd", new ComandoCd(sistema));
        comandos.put("Dir", new ComandoDir(sistema));
        comandos.put("Time", new ComandoTime());
        comandos.put("Rd", new ComandoRd(sistema));
        comandos.put("Ren", new ComandoRen(sistema));
        comandos.put("Find", new ComandoFind(sistema));
        comandos.put("Tree", new ComandoTree(sistema));
        comandos.put("Help", new ComandoHelp());
    }

    public String ejecutar(String entrada) {
        if (entrada == null || entrada.trim().isEmpty()) {
            return "";
        }
        String[] partes = entrada.trim().split("\\s+");
        String nombreComando = partes[0];
        Comando comando = comandos.get(nombreComando);
        if (comando == null) {
            return "'" + nombreComando + "' no se reconoce como un comando interno o externo.";
        }
        String[] argumentos = new String[partes.length - 1];
        for (int i = 1; i < partes.length; i++) {
            argumentos[i - 1] = partes[i];
        }
        return comando.ejecutar(argumentos);
    }

    public String getRutaActual() {
        return sistema.getRutaActual();
    }
}
