/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */
public class ComandoExit implements Comando{
    public ComandoExit(SistemaArchivos sistema) {
    }

    @Override
    public String ejecutar(String[] args) {

        if (args.length != 0) {
            return "Uso correcto: Exit";
        }
        return "EXIT_APP";
    }
}
