/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oscar
 */

import java.util.Calendar;

public class ComandoTime implements Comando{
    
    @Override
    public String ejecutar(String[] argumentos){
        Calendar actual = Calendar.getInstance();
        
        return String.format("Hora actual: %02d:%02d:%02d",
                actual.get(Calendar.HOUR_OF_DAY),
                actual.get(Calendar.MINUTE),
                actual.get(Calendar.SECOND));
        
    }
    
}
