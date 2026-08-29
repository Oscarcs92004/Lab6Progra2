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
public class Controlador {
    private SistemaArchivos sistema;
    private InterpreteComandos interprete;
    private boolean modoEscritura;
    private boolean modoAppend;
    private File archivoEscritura;
    private StringBuilder contenido;

    public Controlador() {
        sistema = new SistemaArchivos();
        interprete = new InterpreteComandos(sistema);
        modoEscritura = false;
        modoAppend = false;
    }

    public String procesarEntrada(String entrada) {
        if (modoEscritura) {
            if (entrada.equals("EXIT")) {
                return finalizarEscritura();
            }
            contenido.append(entrada);
            contenido.append(System.lineSeparator());
            return "";
        }
        String resultado = interprete.ejecutar(entrada);
        if (resultado.equals("MODO_ESCRITURA")) {
            iniciarEscritura(entrada, false);
            return "Escriba el contenido del archivo.\n" + "Escriba EXIT para terminar.";
        }

        if (resultado.equals("MODO_APPEND")) {
            iniciarEscritura(entrada, true);
            return "Escriba el contenido que desea agregar.\n" + "Escriba EXIT para terminar.";
        }
        return resultado;
    }

    private void iniciarEscritura(String entrada, boolean append) {
        String[] partes = entrada.split(" ");
        String nombreArchivo = partes[1];
        archivoEscritura = sistema.buscar(nombreArchivo);
        contenido = new StringBuilder();
        modoEscritura = true;
        modoAppend = append;
    }

    private String finalizarEscritura() {
        String resultado;
        if (modoAppend) {
            ComandoAp comando = new ComandoAp(sistema);
            resultado = comando.agregar(archivoEscritura,contenido.toString());
        } else {
            ComandoWr comando = new ComandoWr(sistema);
            resultado = comando.guardar(archivoEscritura,contenido.toString());
        }

        modoEscritura = false;
        modoAppend = false;
        archivoEscritura = null;
        contenido = null;
        return resultado;
    }

    public String getRutaActual() {
        return sistema.getRutaActual();
    }

    public InterpreteComandos getInterprete() {
        return interprete;
    }
    
    
}
